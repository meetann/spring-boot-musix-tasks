package com.stackroute.service;

import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


import java.util.List;

@Qualifier("trackServiceImpl")

@Service
public class TrackServiceImpl implements TrackService {

    private TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository){
        this.trackRepository=trackRepository;
    }
    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if (trackRepository.existsById(track.getId())){
            throw new TrackAlreadyExistsException("Track Already exists");
        }
        else {
            Track savedTrack = trackRepository.save(track);
            return savedTrack;
        }
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
    public Track getTrackByName(String trackName) throws TrackNotFoundException {
        if(trackRepository.findByTrackName(trackName) == null){
            throw new TrackNotFoundException("Track not found!");
        }
        return trackRepository.findByTrackName(trackName);
    }

    @Override
    public void deleteTrack(Track track) throws TrackNotFoundException {
        if (trackRepository.existsById(track.getId())) {
            trackRepository.deleteById(track.getId());
        }
        else {
            throw new TrackNotFoundException("Track doesn't exist!");
        }
    }

    @Override
    public Track updateTrack(int id, String comment) {
        Track track = trackRepository.findById(id).get();
        track.setComments(comment);
        Track updatedTrack = trackRepository.save(track);
        return updatedTrack;
    }
}
