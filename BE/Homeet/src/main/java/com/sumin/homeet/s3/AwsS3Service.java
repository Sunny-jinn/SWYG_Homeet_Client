package com.sumin.homeet.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AwsS3Service {
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    public List<String> uploadFile(List<MultipartFile> multipartFiles){
        List<String> fileList = new ArrayList<>();

        multipartFiles.forEach(f ->{
            String fileName = UUID.randomUUID().toString().concat(f.getOriginalFilename());
            ObjectMetadata omd= new ObjectMetadata();
            omd.setContentLength(f.getSize());
            omd.setContentType(f.getContentType());

            try {
                amazonS3.putObject(new PutObjectRequest(bucket,fileName,f.getInputStream(),omd)
                        .withCannedAcl(CannedAccessControlList.PublicRead));

            }catch (IOException e){
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "file upload fail");
            }
            fileList.add(amazonS3.getUrl(bucket, fileName).toString());
        });
        return fileList;
    }
    public void deleteFile(String fileName){
        amazonS3.deleteObject(new DeleteObjectRequest(bucket,fileName));
    }
}
