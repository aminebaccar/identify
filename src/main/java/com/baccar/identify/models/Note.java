package com.baccar.identify.models;

import javax.persistence.*;

@Entity
@Table(name = "t_notes")
public class Note {
    private Long id_note;
    private String content;
    private Picture picture;
    private String date_created;
    private String date_updated;

    public Note() {
    }

    public Note(Long id_note, String content, Picture picture, String date_created, String date_updated) {
        this.id_note = id_note;
        this.content = content;
        this.picture = picture;
        this.date_created = date_created;
        this.date_updated = date_updated;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId_note() {
        return id_note;
    }

    public void setId_note(Long id_note) {
        this.id_note = id_note;
    }

    @Column(name = "c_content", nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToOne()
    @JoinColumn(name = "picture_id", nullable = false)
    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    @Column(name = "c_date_created")
    public String getDate_created() {
        return date_created;
    }

    public void setDate_created(String date_created) {
        this.date_created = date_created;
    }

    @Column(name = "c_date_updated")
    public String getDate_updated() {
        return date_updated;
    }

    public void setDate_updated(String date_updated) {
        this.date_updated = date_updated;
    }
}
