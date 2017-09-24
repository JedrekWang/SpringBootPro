package com.jedrek.web;

import com.jedrek.entity.Blog;
import com.jedrek.mapper.BlogMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by Jedrek on 2017/6/28.
 */
@Controller
public class BlogController {

    private static final Logger log = Logger.getLogger(BlogController.class);

    private BlogMapper blogMapper;

    @Autowired
    public BlogController(BlogMapper blogMapper) {
        this.blogMapper = blogMapper;
    }


    /**
     * 显示首页
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String index(Model model) {
        List<Blog> list = blogMapper.queryThird(3);
        model.addAttribute("blogs",list);
        return "main";
    }
    /**
     * 通过blogID查找指定的blog
     * @param blogId
     * @param model
     * @return
     */
    @RequestMapping(value = "/blogs/{blogId}",method = RequestMethod.GET)
    public String getBlog(@PathVariable Integer blogId,Model model) {
        Blog blog = blogMapper.queryBlog(blogId);
        model.addAttribute("blog",blog);
        return "blogs/blog";
    }

    /**
     * 指向创建blog页面
     * @return
     */
    @RequestMapping(value = "/blogs/create",method = RequestMethod.GET)
    public String createBlog() {
        return "blogs/create";
    }

    /**
     * 提交blog信息
     * @param blog
     * @return
     */
    @RequestMapping(value = "/blogs/create",method = RequestMethod.POST)
    public String saveBlog(Blog blog) {
        blogMapper.saveBlog(blog);
        return "sus";
    }

    @RequestMapping("/test")
    public String showTest() {
        log.error("I'm running stuff");
        return "test";
    }

}
