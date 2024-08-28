package org.ifarmr.service;

import org.ifarmr.payload.response.CloudinaryResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

    CloudinaryResponse<String> uploadProfilePicture(String email, MultipartFile file);
}
