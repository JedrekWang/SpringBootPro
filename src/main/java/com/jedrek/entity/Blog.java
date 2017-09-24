package com.jedrek.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Jedrek on 2017/6/28.
 */
public class Blog {
    private Integer blogId;

    @Size(min=1,max=20)
    private String title;

    @Size(min=1,max=10)
    private String writer;

    @Size(min = 1,max=50)
    private String blogDescription;

    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date created;

    public Blog() {}

    public Blog(String title,String writer,String blogDescription,String content) {
        this.title = title;
        this.writer = writer;
        this.blogDescription = blogDescription;
        this.content = content;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getBlogDescription() {
        return blogDescription;
    }

    public void setBlogDescription(String blogDescription) {
        this.blogDescription = blogDescription;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "blogId=" + blogId +
                ", title='" + title + '\'' +
                ", writer='" + writer + '\'' +
                ", blogDescription='" + blogDescription + '\'' +
                ", content='" + content + '\'' +
                ", created=" + created +
                '}';
    }
}
