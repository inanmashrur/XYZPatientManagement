package org.xyz.patientmanagement.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author inanmashrur
 * @since 26/03/2023
 */
@Entity
@Table(name = "attachement")
public class Attachment extends Persistent {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attachment")
    @SequenceGenerator(name = "attachment", sequenceName = "attachment_seq", allocationSize = 1)
    private long id;

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotNull
    @Max(1024*1024*5) // 5 MB
    private long size;

    @NotBlank
    private String type;

    @NotNull
    private byte[] data;

    @Transient
    private String dataInBase64;

    public Attachment() {
    }

    public Attachment(String name, long size, String type, byte[] data) {
        this();
        this.name = name;
        this.size = size;
        this.type = type;
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getDataInBase64() {
        return dataInBase64;
    }

    public void setDataInBase64(String dataInBase64) {
        this.dataInBase64 = dataInBase64;
    }
}
