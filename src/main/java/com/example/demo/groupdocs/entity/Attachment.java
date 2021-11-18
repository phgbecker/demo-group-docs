package com.example.demo.groupdocs.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class Attachment {
    @Id
    private String id;
    @Lob
    private byte[] blob;
}
