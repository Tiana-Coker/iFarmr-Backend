package org.ifarmr.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.ifarmr.entity.Post;
import org.ifarmr.entity.User;
import org.ifarmr.exceptions.FileUploadException;
import org.ifarmr.exceptions.NotFoundException;
import org.ifarmr.payload.request.PostRequest;
import org.ifarmr.payload.response.PostResponse;
import org.ifarmr.repository.PostRepository;
import org.ifarmr.repository.UserRepository;
import org.ifarmr.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final Cloudinary cloudinary;

    @Override
    public PostResponse createPost(PostRequest postRequest, String userName) {

        // get the user who created the post
        User user = userRepository.findByUsername(userName).orElse(null);


        if(user == null){
            throw new NotFoundException("user not found");
        }

        String fileUrl = null;
        MultipartFile file = postRequest.getFile();
        if (file != null && !file.isEmpty()) {
            try {
                Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                fileUrl = uploadResult.get("url").toString();
            } catch (Exception e) {
                throw new FileUploadException("Error uploading file to Cloudinary");
            }
        }

        Post post = postRepository.save(Post.builder()
                        .title(postRequest.getTitle())
                        .content(postRequest.getContent())
                        .photoUrl(fileUrl)
                        .user(user)
                .build()
        );

        return PostResponse.builder()
                .message("Post created successfully")
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .photoUrl(fileUrl)
                .build();
    }
}
