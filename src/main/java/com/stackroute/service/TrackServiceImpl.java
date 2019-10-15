package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService {

    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository){
        this.trackRepository=trackRepository;
    }
    @Override
    public Track saveTrack(Track track) {

        Track savedTrack = trackRepository.save(track);
        return savedTrack;
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public Track getTrackById(int id) {
        Track receivedTrack = trackRepository.findById(id).get();
        return receivedTrack;
    }

    @Override
    public void deleteTrack(Track track) {
        trackRepository.deleteById(track.getId());
    }

    @Override
    public Track updateTrack(int id, String comment) {
        Track track = trackRepository.findById(id).get();
        track.setComments(comment);
        Track updatedTrack = trackRepository.save(track);
        return updatedTrack;
    }
}
