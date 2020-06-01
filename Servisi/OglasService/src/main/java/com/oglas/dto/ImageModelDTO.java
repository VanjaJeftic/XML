package com.oglas.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class ImageModelDTO {

    private Long id;
    private String name;
    private String type;
    private Long vozilo_id;
    //image bytes can have large lengths so we specify a value
    //which is more than the default length for picByte column
    private byte[] picByte;

    public ImageModelDTO() {
    }

    public ImageModelDTO(String name, String type, byte[] picByte,Long vozilo_id) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
        this.vozilo_id=vozilo_id;
    }

    public ImageModelDTO(Long id, String name, String type, byte[] picByte, Long vozilo_id) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.picByte = picByte;
        this.vozilo_id=vozilo_id;
    }

    public Long getVozilo_id() {
        return vozilo_id;
    }

    public void setVozilo_id(Long vozilo_id) {
        this.vozilo_id = vozilo_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
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
