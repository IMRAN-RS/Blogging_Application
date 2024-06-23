package com.springapi.blog_application.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class fileServiceImpl implements fileService {


    @Override
    public String uploadImage(String Path, MultipartFile file) throws IOException {
        return "";
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        return null;
    }
}
