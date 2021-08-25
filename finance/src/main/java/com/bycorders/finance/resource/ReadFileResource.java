package com.bycorders.finance.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class ReadFileResource {

    @PostMapping("/read")
    public ResponseEntity<String> read(@RequestParam MultipartFile file) {
        return ResponseEntity.ok().body(file.getOriginalFilename());
    }

}
