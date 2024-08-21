package org.ifarmr.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.ifarmr.service.GlobalUploadService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
@RequiredArgsConstructor
public class GlobalUploadServiceImpl implements GlobalUploadService {

    private final Cloudinary cloudinary;

    @Override
     public String uploadImage(MultipartFile multipartFile) throws IOException {

        return cloudinary.uploader()
                .upload(multipartFile.getBytes(), ObjectUtils.emptyMap())
                .get("secure_url")
                .toString();
    }
}
