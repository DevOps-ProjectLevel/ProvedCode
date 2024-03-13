package com.provedcode.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.apache.http.entity.ContentType.*;


@Service
@AllArgsConstructor
public class PhotoService {
    public File degradePhoto(File photoFile) throws IOException {
        // Load image from file
        BufferedImage originalImage = ImageIO.read(photoFile);

        // New size for image
        int newWidth = 300;
        int newHeight = 300;

        // Creating new image with new size
        Image resizedImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        // создаем новый файл для уменьшенного изображения
        String fileName = photoFile.getName();
        String fileType = fileName.substring(fileName.lastIndexOf('.') + 1);
        File newFile = new File(photoFile.getParent(), fileName);

        // записываем уменьшенное изображение в новый файл
        BufferedImage outputImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.createGraphics().drawImage(resizedImage, 0, 0, null);
        ImageIO.write(outputImage, fileType, newFile);

        // возвращаем новый файл
        return newFile;
    }

    public boolean isFileImage(MultipartFile file) {
        return !List.of(IMAGE_JPEG.getMimeType(), IMAGE_PNG.getMimeType(), IMAGE_GIF.getMimeType())
                    .contains(file.getContentType());
    }
}
