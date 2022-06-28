package com.chimichurris.server.models.dtos;

import javax.validation.constraints.Min;

public class viewMessagesDTO {
    @Min(value = 0)
    private long idSender;
    @Min(value = 0)
    private long idReceiver;

    public viewMessagesDTO() {
        super();
    }

    public viewMessagesDTO(long idSender, long idReceiver) {
        super();
        this.idSender = idSender;
        this.idReceiver = idReceiver;
    }

    public long getIdSender() {
        return idSender;
    }

    public void setIdSender(long idSender) {
        this.idSender = idSender;
    }

    public long getIdReceiver() {
        return idReceiver;
    }

    public void setIdReceiver(long idReceiver) {
        this.idReceiver = idReceiver;
    }
}
