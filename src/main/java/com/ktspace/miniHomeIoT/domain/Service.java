package com.ktspace.miniHomeIoT.domain;

import java.time.LocalDateTime;

public class Service {
    private Integer seq;
    private LocalDateTime createDate;

    //region getter and setter
    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
    //endregion

}
