package com.springapi.blog_application.ApiResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Apiresponse {
    private String message;
    private boolean HttpStatus;
}
