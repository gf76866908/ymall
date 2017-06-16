package com.ymall.controller.portal;

import com.google.common.collect.Maps;
import com.ymall.common.ServerResponse;
import com.ymall.common.exception.IllegalException;
import com.ymall.service.FileService;
import com.ymall.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by zc on 2017/6/16.
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;


    @RequestMapping(value = "upload_img", method = RequestMethod.POST)
    public ServerResponse upload(HttpServletRequest request) throws IllegalException {

        if (request instanceof MultipartHttpServletRequest) {

            MultipartFile file = ((MultipartHttpServletRequest) request).getFile("file");

            if (file != null) {
                //定义临时文件夹
                String tmp_path = request.getSession().getServletContext().getRealPath("tmp");
                String targetFileName = fileService.upload(file, tmp_path, PropertiesUtil.getProperty("cos.path.prefix"));
                String url = PropertiesUtil.getProperty("cos.server.http.prefix") + targetFileName;

                Map fileMap = Maps.newHashMap();
                fileMap.put("uri", targetFileName);
                fileMap.put("url", url);
                return ServerResponse.createBySuccess(fileMap);
            }

            throw new IllegalException("文件不能为空");

        } else {
            throw new IllegalException("请求不是文件");
        }

    }
}
