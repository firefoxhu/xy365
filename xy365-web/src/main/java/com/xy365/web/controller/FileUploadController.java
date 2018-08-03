package com.xy365.web.controller;
import com.xy365.core.properties.XyProperties;
import com.xy365.core.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin("*")
@RequestMapping("/xy/")
public class FileUploadController {



    @Autowired
    private XyProperties xyProperties;

    @PostMapping("upload")
    public String upload(MultipartFile file){

        try {
            return FileUtil.localUpload2(xyProperties.getFileConfig().getTempDir(),file.getOriginalFilename(),file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败！";
    }



}
