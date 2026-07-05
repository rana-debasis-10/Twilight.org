package com.twilight.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;

public interface StorageService {

    public void upload(MultipartFile file,String key) throws IOException;
    public Resource download(String key) ;
    public void delete(String key) throws IOException ;
    public default String generateKey(String folder, String fileName){
        return folder + "/" + UUID.randomUUID()+"-"+fileName;
    }
}

