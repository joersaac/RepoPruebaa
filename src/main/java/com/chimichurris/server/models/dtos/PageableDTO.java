package com.chimichurris.server.models.dtos;

import javax.validation.constraints.Min;

public class PageableDTO {
    private long userID;

    @Min(0)
    private int page;

    @Min(0)
    private int limit;

    public PageableDTO() {
        super();

        this.page = 0;
        this.limit = 10;
    }

    public PageableDTO(int page, int limit) {
        super();
        this.userID = 0;
        this.page = page;
        this.limit = limit;
    }

    public PageableDTO(long userID, int page, int limit) {
        super();
        this.userID = userID;
        this.page = page;
        this.limit = limit;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}