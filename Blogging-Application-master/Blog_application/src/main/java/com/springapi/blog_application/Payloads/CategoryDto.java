package com.springapi.blog_application.Payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private int Categoryid;
    @NotBlank
    @Size(min = 2, max = 50)
    private String Categorytitle;
    @NotBlank
    @Size(min = 10, max = 50)
    private String CategoryDescription;
}
