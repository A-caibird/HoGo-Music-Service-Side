package com.spring.ScheduledTask;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class DatabaseBackupTask {
    //@Scheduled(fixedDelay = 5000) // 每5秒执行一次
    @Scheduled(cron = "0 0 0 * * ?") // 每天凌晨0点0分0秒执行一次
    public void backupDatabase() {
        try {
            // 构建命令
            String[] command = {"mysqldump", "-u", "root", "-p", "WebMusic"};

            // 启动进程
            Process process = Runtime.getRuntime().exec(command);

            // 自动输入密码
            String password = "775028";
            OutputStream outputStream = process.getOutputStream();
            outputStream.write(password.getBytes());
            outputStream.flush();
            outputStream.close();

            // 备份文件路径
            String backupFilePath = "/Users/acaibird/Documents/CodeExperimentSpace/Java/spring/src/main/resources/static/mysql/" + getCurrentTime() + ".sql";

            // 读取命令输出并写入备份文件
            InputStream inputStream = process.getInputStream();
            OutputStream fileOutputStream = new FileOutputStream(backupFilePath);
            IOUtils.copy(inputStream, fileOutputStream);

            // 等待命令执行完成
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                log.info("数据库备份完成");
            } else {
                log.info("数据库备份失败");
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        return now.format(formatter);
    }
}
