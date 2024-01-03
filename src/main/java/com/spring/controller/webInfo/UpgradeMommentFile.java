package com.spring.controller.webInfo;

import com.spring.dao.Avatar;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UpgradeMommentFile {
    private Avatar avatar;
    private final String blogDir = "/Users/acaibird/Documents/blog/source/_posts";

    @Autowired
    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    @PostMapping("/uploadMdFile")
    public ResponseEntity<?> handler(@RequestParam("md") MultipartFile file, @RequestParam("name") String name) throws IOException, InterruptedException {
        String Name = file.getOriginalFilename();
        assert Name != null;
        log.info("md文件传送成功!");

        String filePath = "/Users/acaibird/Documents/blog/source/_posts/" + Name;

        File destinationFile = new File(filePath);
        // 如果同名文件存在，先删除原来的文件
        if (destinationFile.exists()) {
            destinationFile.delete();
            log.info("网站博客: 更新网站博客!");
        }

        try {
            avatar.insert(name, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        file.transferTo(destinationFile);

        // 执行命令
        executeCommand("hexo server");
        return new ResponseEntity<>("http://localhost:40000/", HttpStatusCode.valueOf(200));
    }

    private void executeCommand(String command) throws IOException, InterruptedException {
        // 创建进程构建器
        ProcessBuilder processBuilder = new ProcessBuilder();

        // 将命令分割成字符串数组
        String[] commandArray = {"/bin/zsh", "-c", command};

        // 设置命令和工作目录
        processBuilder.command(commandArray);
        processBuilder.directory(new File(blogDir));

        // 启动进程
        Process process = processBuilder.start();

        // 等待命令执行完成
        int exitCode = process.waitFor();

        // 如果服务已经启动，先暂停再执行命令
        if (exitCode == 0) {
            // 模拟按下Ctrl+C
            process.destroy();

            // 等待进程终止
            process.waitFor();

            executeCommand("hexo server");
        }
    }
}
