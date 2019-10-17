package com.stackroute.controller;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = { "classpath:application.properties" })

public class ApplicationStarter implements ApplicationListener<ApplicationReadyEvent> {

    /**
     * This event is executed as late as conceivably possible to indicate that
     * the application is ready to service requests.
     */

    @Value("${track.id}")
    private int id;
    @Value("${track.trackname}")
    private String trackName;
    @Value("${track.comments}")
    private String comments;

    @Autowired
    private TrackRepository trackRepository;
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        seedData();
    }
    private void seedData() {
        Track track = new Track();
        track.setId(id);
        track.setTrackName(trackName);
        track.setComments(comments);
        trackRepository.save(track);

//        trackRepository.save(new Track(1,"Perfect","Ed"));
//        trackRepository.save(new Track(52,"22","Music"));
    }
}