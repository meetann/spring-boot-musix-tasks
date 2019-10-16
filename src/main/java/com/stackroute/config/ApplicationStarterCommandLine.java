package com.stackroute.config;

import com.stackroute.domain.Track;
import com.stackroute.repository.TrackRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationStarterCommandLine {

@Bean
public CommandLineRunner loadData(TrackRepository trackRepository) {
        return (args) -> {
            // save a couple of tracks
            trackRepository.save(new Track(31, "22", "Swift"));
        };
    }
}
