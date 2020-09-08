package com.mammoth.journal.model;


import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;


@Entity
@EntityScan
public class Entry implements Comparable<Entry>{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long entryId;

    @NotNull(message="Title Cannot be Null")
    @NotEmpty(message="Title Cannot be Empty")
    private String title;

    @Lob
    private String summary;

    @Lob
    @NotNull(message="Body Cannot be Null")
    @NotEmpty(message="Body Cannot be Empty")
    private String body;

    @ManyToOne
    private JournalUser journalUser;

    private Date date;
    private Timestamp time;

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

    public void setTitle(String title) {
        this.title = title;
    }
    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public int compareTo(Entry entry) {
        if(this.getDate().compareTo(entry.getDate()) ==0){
            return this.getTime().compareTo(entry.getTime());
        }
        return this.getDate().compareTo(entry.getDate());
    }

}
