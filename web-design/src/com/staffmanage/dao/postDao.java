package com.staffmanage.dao;

import com.staffmanage.entity.Post;

import java.util.List;

public interface postDao {
    //获得所有岗位
    List<Post> getAllPost();

}
