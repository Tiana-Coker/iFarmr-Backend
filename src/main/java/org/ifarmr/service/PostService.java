package org.ifarmr.service;


import org.ifarmr.entity.Post;
import org.ifarmr.payload.request.PostRequest;
import org.ifarmr.payload.response.PopularPostResponse;
import org.ifarmr.payload.response.PostResponse;

import java.util.List;

public interface PostService {

    PostResponse createPost(PostRequest postRequest, String userName);

     List<PopularPostResponse> getPopularPosts();

}
