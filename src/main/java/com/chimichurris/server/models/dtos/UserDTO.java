package com.chimichurris.server.models.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDTO {

    private long id;

    private String username;

    private String name;

    private String rol;

    private String img;

    private int InternalHours;

    private int ExternalHours;

    public UserDTO() {
        super();
    }

    public UserDTO(long id, String username, String name, String rol, String img, int internalHours, int externalHours) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.rol = rol;
        this.img = img;
        InternalHours = internalHours;
        ExternalHours = externalHours;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getInternalHours() {
        return InternalHours;
    }

    public void setInternalHours(int internalHours) {
        InternalHours = internalHours;
    }

    public int getExternalHours() {
        return ExternalHours;
    }

    public void setExternalHours(int externalHours) {
        ExternalHours = externalHours;
    }
}
