package com.stackroute.service;

import com.stackroute.domain.Track;

import java.util.List;

public interface TrackService {

    public Track saveTrack(Track track);

    public List<Track> getAllTracks();

    public Track getTrackById(int id);

    public void deleteTrack(Track track);

    public Track updateTrack(int id, String comment);
}
