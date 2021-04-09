package com.baccar.identify.models;

import javax.persistence.*;

@Entity
@Table(name = "t_pictures")
public class Picture {
    private Long id;
    private String file_location;
    private String name;
    private Integer threat;
    private String date_created;
    private String date_updated;

    public Picture() {
    }

    public Picture(Integer id) {
        this.id = id + 0L;
    }

    public Picture(Long id, String file_location, String name, Integer threat, String date_created, String date_updated) {
        this.id = id;
        this.file_location = file_location;
        this.name = name;
        this.threat = threat;
        this.date_created = date_created;
        this.date_updated = date_updated;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "c_name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "c_threat", nullable = false)
    public Integer getThreat() {
        return threat;
    }

    public void setThreat(Integer threat) {
        this.threat = threat;
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

    @Column(name = "c_file_location", nullable = false)
    public String getFile_location() {
        return file_location;
    }

    public void setFile_location(String file_location) {
        this.file_location = file_location;
    }
}
