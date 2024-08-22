package org.ifarmr.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface GlobalUploadService {

    String uploadImage(MultipartFile multipartFile) throws IOException;
}
