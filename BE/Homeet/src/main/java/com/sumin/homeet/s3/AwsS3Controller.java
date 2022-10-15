package com.sumin.homeet.s3;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AwsS3Controller {
    private final AwsS3Service awsS3Service;

    @PostMapping("/room/test/file")
    public ResponseEntity upload(@RequestPart("images") List<MultipartFile> multipartFiles){
        System.out.println(" = ");
        System.out.println("multipartFiles = " + multipartFiles);
        List<String> urlLists = awsS3Service.uploadFile(multipartFiles);
        System.out.println("urlLists = " + urlLists);
        String json = new Gson().toJson(urlLists);
        return new ResponseEntity(json, HttpStatus.OK);
    }

    @DeleteMapping("/room/test/url/del")
    public ResponseEntity delete(@RequestParam("url") String url){
        awsS3Service.deleteFile(url);
        String json = new Gson().toJson("ok");
        return new ResponseEntity(json, HttpStatus.OK);
    }
}
