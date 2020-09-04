package com.mammoth.journal.model;


import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;


@Entity
@EntityScan
public class Entry {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long entryId;

    private String title;
    private String summary;
    private String body;

    @ManyToOne
    private JournalUser journalUser;


    public Long getEntryId() {
        return entryId;
    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
    }

    public JournalUser getJournalUser() {
        return journalUser;
    }

    public void setJournalUser(JournalUser journalUser) {
        this.journalUser = journalUser;
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
