package com.mammoth.journal.model;


import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;


@Entity
public class Entry {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long entryId;

    private String title;
    private String summary;
    private String body;

    @ManyToOne
    private User user;


    public Long getEntryId() {
        return entryId;
    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return this.body;
    }

    public void setTitle(String Title) {
        this.title = title;
    }
    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
