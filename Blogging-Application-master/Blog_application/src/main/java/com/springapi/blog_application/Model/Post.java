package com.springapi.blog_application.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer postId;

    @Column( length = 100 ,nullable =false)
    private String Title;

    @Column( length = 1000 ,nullable =false)
    private String Content;
    private String imageName;
    private Date PostDate;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne()
    private User user;


}
