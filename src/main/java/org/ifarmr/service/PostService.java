package org.ifarmr.service;

import org.ifarmr.payload.request.PostRequest;
import org.ifarmr.payload.response.PostResponse;

public interface PostService {

    PostResponse createPost(PostRequest postRequest, String userName);
}
