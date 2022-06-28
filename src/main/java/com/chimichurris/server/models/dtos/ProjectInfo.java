package com.chimichurris.server.models.dtos;

import javax.swing.plaf.SeparatorUI;
import javax.validation.constraints.*;

public class ProjectInfo {

    @NotBlank
    @Size(min = 4, max = 32)
    private String name;

    @NotBlank
    @Size(min = 4, max = 150)
    private String description;

    private String hour_type;

    private String contactEmail;

    @Min(value =5)
    @Max(value=50)
    private int quota;

    private int hours;

    public ProjectInfo(String name, String description, String hour_type, String contactEmail, int quota, int hours) {
        super();
        this.name = name;
        this.description = description;
        this.hour_type = hour_type;
        this.contactEmail = contactEmail;
        this.quota = quota;
        this.hours = hours;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHour_type() {
        return hour_type;
    }

    public void setHour_type(String hour_type) {
        this.hour_type = hour_type;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public int getQuota() {
        return quota;
    }

    public void setQuota(int quota) {
        this.quota = quota;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

}
