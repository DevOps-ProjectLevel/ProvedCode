package com.provedcode.aws.service;


import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.provedcode.config.AWSProperties;
import com.provedcode.talent.model.entity.Talent;
import com.provedcode.talent.repo.TalentRepository;
import com.provedcode.user.model.entity.UserInfo;
import com.provedcode.user.repo.UserInfoRepository;
import com.provedcode.util.PhotoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class S3Service implements FileService {
    AWSProperties awsProperties;
    AmazonS3 amazonS3;
    UserInfoRepository userInfoRepository;
    TalentRepository talentRepository;
    PhotoService photoService;

    @Override
    public String saveFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        try {
            File file1 = convertMultiPartToFile(file);
            PutObjectResult objectResult = amazonS3.putObject(awsProperties.bucket(), originalFilename, file1);
            return objectResult.getContentMd5();
        } catch (IOException e) {
            throw new ResponseStatusException(NOT_IMPLEMENTED);
        }
    }

    @Override
    public byte[] downloadFile(String fullFilePath) {
        S3Object object = amazonS3.getObject(awsProperties.bucket(), fullFilePath);
        S3ObjectInputStream objectContent = object.getObjectContent();
        try {
            return IOUtils.toByteArray(objectContent);
        } catch (IOException e) {
            throw new ResponseStatusException(NOT_IMPLEMENTED);
        }
    }

    @Override
    public String deleteFile(String fullFilePath) {
        amazonS3.deleteObject(awsProperties.bucket(), fullFilePath);
        return "file deleted";
    }

    @Override
    public List<String> listAllFiles() {
        ListObjectsV2Result listObjectsV2Result = amazonS3.listObjectsV2(awsProperties.bucket());
        return listObjectsV2Result.getObjectSummaries().stream().map(S3ObjectSummary::getKey).toList();
    }

    @Override
    public void setNewUserImage(MultipartFile file, Long talentId, Authentication authentication) {
        if (file.isEmpty()) {
            throw new ResponseStatusException(BAD_REQUEST, "file must be not empty, actual file-size: %s".formatted(file.getSize()));
        }
        if (photoService.isFileImage(file)) {
            throw new ResponseStatusException(BAD_REQUEST, "not supported type: %s".formatted(file.getContentType()));
        }
        UserInfo user = userInfoRepository.findByLogin(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "user with login = {%s} not found".formatted(authentication.getName())));
        Talent talent = talentRepository.findById(talentId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "talent with id = {%s} not found".formatted(talentId)));
        if (!user.getTalent().equals(talent)) {
            throw new ResponseStatusException(FORBIDDEN, "You cannot change another talent");
        }

        try {
            String fileType = getFileType(file);
            String fullPath = getFullPath(fileType, authentication.getName());
            File degradePhoto = photoService.degradePhoto(convertMultiPartToFile(file));

            if (user.getTalent().getImageName() != null) // delete old user image
                amazonS3.deleteObject(awsProperties.bucket(), user.getTalent().getImageName());

            amazonS3.putObject(awsProperties.bucket(), fullPath, degradePhoto);

            user.getTalent().setImageName(fullPath);

            URL url = generetePresingedUrlFor7Days(fullPath); // generation url with expiration

            log.info("image = {}", amazonS3.getUrl(awsProperties.bucket(), fullPath).toString());
            log.info("image-url = {}", url);

            user.getTalent().setImage(url.toString());

            talentRepository.save(user.getTalent());
        } catch (AmazonS3Exception | IOException e) {
            throw new ResponseStatusException(SERVICE_UNAVAILABLE, "problems with connection to aws s3");
        }

    }

    public URL generetePresingedUrlFor7Days(String fileFullPath) {
        GeneratePresignedUrlRequest urlRequest = new GeneratePresignedUrlRequest(awsProperties.bucket(), fileFullPath)
                .withMethod(HttpMethod.GET);

        Instant expiration = Instant.now().plusMillis(1000L * 60 * 60 * 24 * 7); // expiration time
        urlRequest.setExpiration(Date.from(expiration));
        return amazonS3.generatePresignedUrl(urlRequest); // generation url with expiration
    }

    private String getFullPath(String fileType, String userLogin) {
        return "%s/%s".formatted(userLogin, "image.%s".formatted(fileType));
    }

    private String getFileType(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        return fileName != null ? fileName.substring(fileName.lastIndexOf('.') + 1) : null;
    }

    private File convertMultiPartToFile(MultipartFile file)
            throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}