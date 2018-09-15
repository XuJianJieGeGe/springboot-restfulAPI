package com.xjj.controller;

import com.xjj.entity.FileInfo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * Created by jie on 2018/9/9.
 */
@RestController
@RequestMapping("/file")
public class FileController {

    //文件上传的路径
    private String folder = "C:\\Users\\jie\\Desktop\\springBoot-master\\springboot-restfulAPI\\src\\main\\java\\com\\xjj\\controller";


    //文件的上传
    @PostMapping
    public FileInfo fileUpload(MultipartFile file) throws Exception{
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());


        //文件保存的路径
        File localFile = new File(folder,new Date().getTime()+".txt");
        file.transferTo(localFile);

        return new FileInfo(localFile.getAbsolutePath());
    }

    //文件的下载
    @GetMapping("/{id}")
    public void downLoad(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception{
        InputStream inputStream = new FileInputStream(new File(folder,id+".txt"));
        OutputStream outputStream = response.getOutputStream();
        response.setContentType("application/x-download");
        response.addHeader("Content-Disposition","attachment:filename-test.txt");

        IOUtils.copy(inputStream,outputStream);//把输入的内容输出
        outputStream.flush();

    }




}
