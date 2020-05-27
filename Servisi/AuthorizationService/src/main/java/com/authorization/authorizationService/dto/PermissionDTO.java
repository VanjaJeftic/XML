package com.authorization.authorizationService.dto;

import javax.persistence.Column;

public class PermissionDTO {

    private Long id;
    private String name;

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

    public PermissionDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
