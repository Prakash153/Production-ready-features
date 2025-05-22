package com.prakash.prod_ready_features.services;

import com.prakash.prod_ready_features.dto.PostDTO;

import java.util.List;

public interface PostSerivce {

    List<PostDTO> getAllPosts();

    PostDTO getPostById(Long postId);

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO updatePost(PostDTO inputPost, Long postId);
}
