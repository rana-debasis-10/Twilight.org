package com.twilight.serviceImpls;

import com.twilight.exceptions.NotFoundException;
import com.twilight.exceptions.SomethingWentWrongException;
import com.twilight.services.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Slf4j
@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private S3Client s3Client;

    @Value("${storage.client.bucket}")
    private String bucket;

    public void upload(MultipartFile file,String key) throws IOException {

        try {
            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket(bucket)
                            .key(key)
                            .contentType(file.getContentType())
                            .build(),
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize())
            );
        } catch (AwsServiceException | SdkClientException | IOException e) {
            throw new SomethingWentWrongException(
                    e.getMessage(),
                    "Unable to upload image"
            );
        }
    }

    public Resource download(String key) {
        byte[] data = null;
        try {
            data = s3Client.getObjectAsBytes(
                    GetObjectRequest.builder()
                            .bucket(bucket)
                            .key(key)
                            .build()
            ).asByteArray();
        } catch (AwsServiceException |SdkClientException e) {
            throw new NotFoundException();
        } catch (Exception e) {
            throw new SomethingWentWrongException(
                    e.getMessage()
                    ,"Something went wrong");
        }

        return new ByteArrayResource(data);
    }

    public void delete(String key) throws IOException {
        try {
            s3Client.deleteObject(
                    DeleteObjectRequest.builder()
                            .bucket(bucket)
                            .key(key)
                            .build()
            );
        } catch (AwsServiceException |SdkClientException e) {
            throw new NotFoundException();
        } catch (Exception e) {
            throw new SomethingWentWrongException(
                    e.getMessage()
                    ,"Something went wrong");
        }

    }
}
