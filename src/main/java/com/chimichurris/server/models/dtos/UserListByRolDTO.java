package com.chimichurris.server.models.dtos;

public class UserListByRolDTO {

    private long id;

    private String username;

    private String img;

    public UserListByRolDTO() {
        super();
    }

    public UserListByRolDTO(long id, String username, String img) {
        super();
        this.id = id;
        this.username = username;
        this.img = img;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
