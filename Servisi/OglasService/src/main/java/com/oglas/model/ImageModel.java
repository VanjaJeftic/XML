package com.oglas.model;

import com.oglas.dto.ImageModelDTO;

import javax.persistence.*;

@Entity
@Table(name = "image_table")
public class ImageModel {

    public ImageModel() {
        super();
    }
    public ImageModel(String name, String type, byte[] picByte,Long vozilo_id) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
        this.vozilo_id=vozilo_id;
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "type")
    private String type;
    @Column(name = "vozilo_id")
    private Long vozilo_id;
        //image bytes can have large lengths so we specify a value
        //which is more than the default length for picByte column
    @Column(name = "picByte", length = 1000)
    private byte[] picByte;
    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVozilo_id() {
        return vozilo_id;
    }

    public void setVozilo_id(Long vozilo_id) {
        this.vozilo_id = vozilo_id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public byte[] getPicByte() {
        return picByte;
    }
    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }

}
