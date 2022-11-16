package com.pavlitz.pillar_client;

import java.sql.Date;

public class Phrase {

    private Long id;
    private Date creationDate;
    private String answerBody;
    private String pillarType;

    public Phrase() {
    }

    public Phrase(Date creationDate, String answerBody, String pillarType) {
        this.creationDate = creationDate;
        this.answerBody = answerBody;
        this.pillarType = pillarType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getAnswerBody() {
        return answerBody;
    }

    public void setAnswerBody(String answerBody) {
        this.answerBody = answerBody;
    }

    public String getPillarType() {
        return pillarType;
    }

    public void setPillarType(String pillarType) {
        this.pillarType = pillarType;
    }

    @Override
    public String toString() {
        return "Phrase{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", answerBody='" + answerBody + '\'' +
                ", pillarType='" + pillarType + '\'' +
                '}';
    }
}
