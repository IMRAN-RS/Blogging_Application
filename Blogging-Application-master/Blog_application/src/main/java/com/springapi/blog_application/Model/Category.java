package com.springapi.blog_application.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Categoryid;

    @Column(name = "title" , length = 100 )
    private String Categorytitle;
    @Column(name = "Description")
    private String CategoryDescription;

    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Post> posts = new ArrayList<>();
}
