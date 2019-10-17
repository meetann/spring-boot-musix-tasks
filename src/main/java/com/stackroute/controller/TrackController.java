package com.stackroute.controller;


import com.stackroute.domain.Track;
import com.stackroute.exceptions.TrackAlreadyExistsException;
import com.stackroute.exceptions.TrackNotFoundException;
import com.stackroute.service.TrackService;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/")
@RestController
public class TrackController {

   private TrackService trackService;

    public TrackController(TrackService trackService){
        this.trackService = trackService;
    }

    @PostMapping("track")
    public ResponseEntity<?> saveTrack (@RequestBody Track track){
        ResponseEntity responseEntity;
        try {
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity<String>("Successfully Created!",HttpStatus.CREATED);
        }catch (TrackAlreadyExistsException e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return  responseEntity;
    }

    @GetMapping("track")
    //RequestBody annotation of reference Track is not required
    public ResponseEntity<?> getAllTracks(){
        ResponseEntity responseEntity;
        try {
            trackService.getAllTracks();
            responseEntity = new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
        }catch (Exception e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("/name/{trackName}")
    public ResponseEntity<?> getTrackByName(@PathVariable String trackName){
        ResponseEntity responseEntity;
        try{
            responseEntity = new ResponseEntity<Track>(trackService.getTrackByName(trackName),HttpStatus.CREATED);
        }catch (TrackNotFoundException e){
            responseEntity = new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @DeleteMapping("track")
    public ResponseEntity<?> deleteTrack(@RequestBody Track track){
        ResponseEntity responseEntity;
        try {
            trackService.deleteTrack(track);
            responseEntity = new ResponseEntity<String>("Successfully Deleted!",HttpStatus.ACCEPTED);
        }catch (TrackNotFoundException e){
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
        return responseEntity;
    }

//    @DeleteMapping("track/{trackId}")
//    public void deleteTrack(@PathVariable("trackId") int trackId) {
//        ResponseEntity responseEntity;
//        try {
//            trackService.deleteTrack(trackId);
//            responseEntity = new ResponseEntity<String>("Successfully deleted!", HttpStatus.OK);
//        } catch (TrackNotFoundException e) {
//            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
//        }
//    }

    @PutMapping("track/{id}/{comment}")
    public ResponseEntity<?> updateTrack(@PathVariable int id, @PathVariable String comment) {
        ResponseEntity responseEntity;
        try {
            trackService.updateTrack(id,comment);
            responseEntity = new ResponseEntity<>(trackService.getTrackById(id), HttpStatus.CREATED);
        }
        catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
}
