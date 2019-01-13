package com.gbzt.standard.appdeployer.controller;

import com.gbzt.standard.appdeployer.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("/upload")
@PropertySource("classpath:app.properties")
public class UploadController {

    @Autowired
    private Environment env;

    @RequestMapping("/page")
    public String page(){
        return "upload";
    }

   /* @RequestMapping(value="/upload1",method = RequestMethod.POST)
    public ResponseEntity<Message> upload(MultipartRequest mq, HttpServletRequest request){
        Message message = new Message(true,"上传成功",null);
        Map<String, MultipartFile> fileMap = mq.getFileMap();
        Iterator<String> fileIter = fileMap.keySet().iterator();
        String pagename=request.getParameter("pagename");
        System.out.println("pagename ========= "+pagename);
        while(fileIter.hasNext()){
            String fileKey = fileIter.next();
            MultipartFile mpfile = fileMap.get(fileKey);
            String originalFileName = mpfile.getOriginalFilename();
            try {
                File outfile = new File("/home/jaken/documents/temp/"+originalFileName);
                if(!outfile.exists()){
                    outfile.createNewFile();
                }
                mpfile.transferTo(outfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ResponseEntity<Message> responseEntity = ResponseEntity.status(200).body(message);
        return responseEntity;
    }*/

    @RequestMapping(value="/upload2",method = RequestMethod.POST)
    public ResponseEntity<Message> upload1(@RequestParam("files") MultipartFile[] files, HttpServletRequest request){
        Message message = new Message(true,"上传成功",null);
        String localDir = env.getProperty("upload.localpath");

        String pagename=request.getParameter("pagename");
        System.out.println("pagename ========= "+pagename);
//        for(MultipartFile mpfile : files){
//            String originalFileName = mpfile.getOriginalFilename();
//            try {
//                File outfile = new File(localDir+originalFileName);
//                if(!outfile.exists()){
//                    outfile.createNewFile();
//                }
//                mpfile.transferTo(outfile);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        File inFile = new File("/home/jaken/documents/pics/headphone/timg.jpeg");
        File outFile = new File("/home/jaken/documents/temp/aaa.jpg");

        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            if(!outFile.exists()){
                outFile.createNewFile();
            }
            fis = new FileInputStream(inFile);
            fos = new FileOutputStream(outFile);
            byte[] filearr = new byte[4096];
            int length = 0;
            while((length = fis.read(filearr)) != -1){
                fos.write(filearr,0,length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        ResponseEntity<Message> responseEntity = ResponseEntity.status(200).body(message);
        return responseEntity;
    }
}
