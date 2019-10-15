package com.stackroute.domain;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Track {
    @Id
    int id;
    String trackName;
    String comments;

    public Track(){

    }

    public Track(int id, String trackName, String comments) {
        this.id = id;
        this.trackName = trackName;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
