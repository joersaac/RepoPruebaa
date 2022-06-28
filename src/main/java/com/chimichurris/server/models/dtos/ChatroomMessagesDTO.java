package com.chimichurris.server.models.dtos;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

public class ChatroomMessagesDTO {
    @NotBlank
    private String content;

    private long idSender;

    private long idReceiver;

    private Timestamp time;

    public ChatroomMessagesDTO(String content, long idSender, long idReceiver,Timestamp time) {
        super();
        this.content = content;
        this.idSender = idSender;
        this.idReceiver = idReceiver;
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
