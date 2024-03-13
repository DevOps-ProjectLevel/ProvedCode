package com.provedcode.aws.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;

public interface FileService {

    String saveFile(MultipartFile file);

    byte[] downloadFile(String filename);

    String deleteFile(String filename);

    List<String> listAllFiles();

    void setNewUserImage(MultipartFile file, Long talentId, Authentication authentication);

    URL generetePresingedUrlFor7Days(String fileFullPath);
}
