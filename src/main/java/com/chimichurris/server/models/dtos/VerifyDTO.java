package com.chimichurris.server.models.dtos;

public class VerifyDTO {
    private String errors;

    private Boolean exist;

    public VerifyDTO() {
        super();
    }

    public VerifyDTO(String errors, Boolean exist) {
        super();
        this.errors = errors;
        this.exist = exist;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public Boolean getExist() {
        return exist;
    }

    public void setExist(Boolean exist) {
        this.exist = exist;
    }
}
