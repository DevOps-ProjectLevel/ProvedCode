package com.provedcode.aws.controller;

import com.provedcode.aws.service.FileService;
import com.provedcode.util.annotations.doc.controller.aws.GetAllAWSBucketFilesDevApiDoc;
import com.provedcode.util.annotations.doc.controller.aws.GetFileInfoDevApiDoc;
import com.provedcode.util.annotations.doc.controller.aws.PostSetNewUserImageApiDoc;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v3/talents")
public class AWSS3BucketController {
    FileService fileService;

    @PostSetNewUserImageApiDoc
    @PreAuthorize("hasRole('TALENT')")
    @PostMapping("/{talent-id}/image/upload")
    public void setNewUserImage(@RequestParam("file") MultipartFile file,
                                @PathVariable("talent-id") Long talentId,
                                Authentication authentication) {
        fileService.setNewUserImage(file, talentId, authentication);
    }

    @GetAllAWSBucketFilesDevApiDoc
    @PreAuthorize("hasRole('TALENT')")
    @GetMapping("/files")
    List<String> getAllFiles() {
        return fileService.listAllFiles();
    }

    @GetFileInfoDevApiDoc
    @PreAuthorize("hasRole('TALENT')")
    @PostMapping("/aws/test-of-filetype")
    String testTypeOfFile(@RequestParam("file") MultipartFile file,
                          Authentication authentication) {
        return Arrays.stream(file.getContentType().split("/")).toList().get(1) + " " + file.getOriginalFilename() +
                " " + file.getName() + " " + file.getResource();
    }
}
