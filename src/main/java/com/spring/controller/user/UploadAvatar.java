package com.spring.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@Slf4j
public class UploadAvatar {

    @PostMapping("/uploadAvatar")
    public ResponseEntity<?> handler(@RequestParam("avatar") MultipartFile file, @RequestParam("name") String name) throws IOException {
        String Name = file.getOriginalFilename();
        assert Name != null;

        String extension = Name.substring(Name.lastIndexOf("."));
        String fileName = name + extension;
        String filePath = "/Users/acaibird/Documents/CodeExperimentSpace/Java/spring/src/main/resources/static/avatar/" + fileName;

        File destinationFile = new File(filePath);
        // 如果同名文件存在，先删除原来的文件
        if (destinationFile.exists()) {
            destinationFile.delete();
            log.info("用户:" + name + " 更新头像");
        }
        file.transferTo(destinationFile);

        return new ResponseEntity<>("http://localhost:8080/avatar/" + fileName, HttpStatusCode.valueOf(200));
    }
}
