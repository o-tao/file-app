package com.web.service;

import com.web.components.FileComponent;
import com.web.mapper.FileDTO;
import com.web.mapper.FileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileMapper fileMapper;
    private final FileComponent fileComponent;

    private int save(MultipartFile multipartFile, String url) {
        Map<String, Object> data = fileComponent.setFile(multipartFile);
        FileDTO dto = FileDTO.builder()
                .nm1(data.get("Name").toString())
                .nm2(data.get("NewName").toString())
                .extension(data.get("Extension").toString())
                .path(url)
                .type("image/png").build();
        fileMapper.save(dto);
        return dto.getNo();
    }

    public Map<String, Object> filesUpload(MultipartFile[] files) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        FileDTO dto = new FileDTO();
        resultMap.put("state", false);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            dto = findByNo(save(files[i], fileComponent.upload2(files[i])));
            list.add(dto.getPath());
        }
        if (list.size() > 0) {
            resultMap.put("state", true);
            resultMap.put("list", list);
            resultMap.put("comment", "저장 성공");
        }
        return resultMap;
    }

    public Map<String, Object> findAll() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("state", false);

        List<String> list = new ArrayList<>();
        list = fileMapper.findAll();
        if (list.size() > 0) {
            resultMap.put("state", true);
            resultMap.put("list", list);
        }
        return resultMap;
    }

    public FileDTO findByNo(int no) {
        return fileMapper.findByNo(no);
    }
}
