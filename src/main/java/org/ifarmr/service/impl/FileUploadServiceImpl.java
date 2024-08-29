package org.ifarmr.service.impl;


import lombok.RequiredArgsConstructor;
import org.ifarmr.entity.User;
import org.ifarmr.exceptions.NotFoundException;
import org.ifarmr.payload.response.CloudinaryResponse;
import org.ifarmr.repository.UserRepository;
import org.ifarmr.service.FileUploadService;
import org.ifarmr.service.GlobalUploadService;
import org.ifarmr.utils.FileUploadUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    private final UserRepository userRepository;
    private final GlobalUploadService globalUploadService;

    @Override

    public CloudinaryResponse<String> uploadProfilePicture(String username, MultipartFile file) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Username not found"));

        String fileUrl;
        try{
            FileUploadUtil.assertAllowed(file, FileUploadUtil.IMAGE_PATTERN);
            fileUrl = globalUploadService.uploadImage(file);
            user.setDisplayPhoto(fileUrl);
            userRepository.save(user);


        } catch (Exception e) {
            throw new RuntimeException("Failed to Upload your file",e);
        }

        return new CloudinaryResponse<>(user.getId(), user.getEmail(), user.getFullName(),fileUrl);
    }

}
