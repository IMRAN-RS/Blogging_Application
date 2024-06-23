package com.springapi.blog_application.Payloads;

import com.springapi.blog_application.Model.Category;
import com.springapi.blog_application.Model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Integer Postid;
    private String title;
    private String content;
    private String imageName;;
    private Date addedDate;

    private CategoryDto category;

    private UserDto user;

}
