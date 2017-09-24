package com.jedrek.mapper;

import com.jedrek.entity.Blog;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

/**
 * Created by Jedrek on 2017/6/28.
 */
@Mapper
public interface BlogMapper {

    int saveBlog(Blog blog);

    Blog queryBlog(Integer blogId);

    int deleteBlog(Integer blogId);

    List<Blog> queryThird(Integer number);

}
