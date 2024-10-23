package com.example.anysale.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Component
public class FileUtil {

    // 이미지 파일을 저장할 경로
    @Value("${filepath}")
    String filepath;

    public String fileUpload(MultipartFile multipartFile) {

        if (multipartFile.isEmpty()) { // 파일이 없으면 메서드 종료
            return null;
        }

        try {
            // 파일 저장 경로
            Path uploadPath = Paths.get(filepath);

            // 디렉토리가 존재하지 않으면 생성
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
                System.out.println("디렉토리를 생성했습니다: " + uploadPath.toAbsolutePath());
            }

            // 저장할 파일 전체 경로 (파일명은 원래 파일명을 클린 처리)
            Path copyOfLocation = uploadPath.resolve(StringUtils.cleanPath(multipartFile.getOriginalFilename()));

            // 파일을 지정한 경로에 저장 (덮어쓰기 허용)
            Files.copy(multipartFile.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("파일 업로드 성공: " + copyOfLocation.getFileName());

            // 업로드된 파일 이름 반환
            return multipartFile.getOriginalFilename();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("파일 업로드 중 오류가 발생했습니다.");
        }
    }
}
