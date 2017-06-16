package com.ymall.service.impl;

import com.google.common.collect.Lists;
import com.ymall.common.exception.IllegalException;
import com.ymall.service.FileService;
import com.ymall.util.COSUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service("fileService")
public class FileServiceImpl implements FileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);


    public String upload(MultipartFile file,String tmp_path,String cosPathPrefix){
        String fileName = file.getOriginalFilename();
        //扩展名
        //abc.jpg
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        logger.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{}",fileName,tmp_path,uploadFileName);

        File fileDir = new File(tmp_path);
        if(!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile = new File(tmp_path,uploadFileName);


        try {
            file.transferTo(targetFile);
            //文件已经上传成功了


            Boolean aBoolean = COSUtil.uploadFile(cosPathPrefix+uploadFileName,targetFile.getAbsolutePath());
            if(!aBoolean){
                throw new IllegalException("COS上传失败");
            }
            //已经上传到Cos

            targetFile.delete();
        } catch (IOException e) {
            logger.error("上传文件异常",e);
            return null;
        } catch (IllegalException e) {
            e.printStackTrace();
        }

        //  /img/ -> img/
        return cosPathPrefix.substring(1,cosPathPrefix.length())+targetFile.getName();
    }


    public static void main(String[] args) {
        String str="/img/";
        System.out.println(str.substring(1,str.length()));
    }

}
