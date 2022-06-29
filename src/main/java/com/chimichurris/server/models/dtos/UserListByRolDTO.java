package com.chimichurris.server.models.dtos;

public class UserListByRolDTO {

    private long id;

    private String username;

    private String img;

    private String name;

    public UserListByRolDTO() {
        super();
    }

    public UserListByRolDTO(long id, String username, String img,String name) {
        super();
        this.id = id;
        this.username = username;
        this.img = img;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
