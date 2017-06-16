package com.ymall.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    String upload(MultipartFile file, String tmp_path,String cosPath);
}
