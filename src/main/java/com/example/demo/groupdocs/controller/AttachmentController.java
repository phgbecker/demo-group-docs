package com.example.demo.groupdocs.controller;

import com.example.demo.groupdocs.service.AttachmentService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/attachment")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity postAttachment(@RequestBody MultipartFile file) throws IOException {
        var id = attachmentService.uploadAttachment(file);
        return ResponseEntity.ok().body(id);
    }

    @GetMapping("{id}")
    public ResponseEntity getAttachment(@PathVariable String id) throws IOException {
        var fileSystemResource = attachmentService.downloadAttachment(id);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + id + "\"").body(fileSystemResource);
    }
}
