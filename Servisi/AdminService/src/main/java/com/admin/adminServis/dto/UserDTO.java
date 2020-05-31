package com.admin.adminServis.dto;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

public class UserDTO {

    private Long id;
    private String username;
    private String uloga;
    private boolean nalogAktiviran;

    public UserDTO() {

    }

    public UserDTO(Long id, String username, String uloga, boolean nalogAktiviran) {
        this.id = id;
        this.username = username;
        this.uloga = uloga;
        this.nalogAktiviran = nalogAktiviran;
    }
//ovaj konstruktor koristim
    public UserDTO(Long id, String uloga, boolean nalogAktiviran) {
        this.id = id;
        this.uloga = uloga;
        this.nalogAktiviran = nalogAktiviran;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }

    public boolean isNalogAktiviran() {
        return nalogAktiviran;
    }

    public void setNalogAktiviran(boolean nalogAktiviran) {
        this.nalogAktiviran = nalogAktiviran;
    }

}
