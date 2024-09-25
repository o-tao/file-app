package com.web.controller;

import com.web.components.FileComponent;
import com.web.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@CrossOrigin(origins = {"http://localhost:3000"})
@Slf4j
@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileComponent fileComponent;
    private final FileService fileService;

    @GetMapping("/")
    public String index() {
        return "File Upload Server !!";
    }

//    /* 단일 파일 */
//    @PostMapping("/fileUpload")
//    public Map<String, Object> fileUpload(@RequestParam("file") MultipartFile file) {
//        log.info("file {}", file);
//        Map<String, Object> resultMap = new HashMap<String, Object>();
//        resultMap.put("state", true);
//        resultMap.put("url", fileComponent.upload2(file));
//        return resultMap;
//    }

    /* 여러 파일을 배열로 받음 */
    @PostMapping("/filesUpload")
    public Map<String, Object> filesUpload(@RequestParam("file") MultipartFile[] files) {
        return fileService.filesUpload(files);
    }

    @GetMapping("/filesRead")
    public Map<String, Object> filesRead() {
        return fileService.findAll();
    }

    @GetMapping("/view")
    public ResponseEntity<?> view(@RequestParam("url") String url) {
        return fileComponent.getFile(url);
    }

}
