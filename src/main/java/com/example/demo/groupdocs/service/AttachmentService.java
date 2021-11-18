package com.example.demo.groupdocs.service;

import com.example.demo.groupdocs.entity.Attachment;
import com.example.demo.groupdocs.repository.AttachmentRepository;
import com.groupdocs.conversion.Converter;
import com.groupdocs.conversion.options.convert.PdfConvertOptions;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AttachmentService {

    private final AttachmentRepository repository;

    public FileSystemResource downloadAttachment(String id) throws IOException {

        Attachment attachment = repository.findById(id).get();

        var fileBytes = attachment.getBlob();
        InputStream in = new ByteArrayInputStream(fileBytes);
        Converter converter = new Converter(in);
        PdfConvertOptions pdfConvertOptions = new PdfConvertOptions();
        final File generatedFile = File.createTempFile(attachment.getId(), ".pdf");

        OutputStream out = new FileOutputStream(generatedFile);
        converter.convert(out, pdfConvertOptions);
        out.close();

        return new FileSystemResource(generatedFile);

    }

    public String uploadAttachment(final MultipartFile file) throws IOException {
        var id = UUID.randomUUID().toString();
        var attachment = new  Attachment();
        attachment.setId(id);
        attachment.setBlob(file.getBytes());
        repository.save(attachment);
        return id;
    }
}
