package com.twilight.endPoints;

import com.twilight.services.StorageService;

import jakarta.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/images")
@Validated
public class StorageEndpoints {
    
    private StorageService storageService;

    @Cacheable( value = "images",
    key = "#key")
    @GetMapping("/download")
    @Transactional
    public ResponseEntity<Resource> download(
            @RequestParam @NotBlank String key) {
        Resource resource = storageService.download(key);
        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + key + "\""
                )
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
