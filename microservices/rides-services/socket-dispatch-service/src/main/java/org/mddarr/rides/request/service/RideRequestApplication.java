package org.mddarr.rides.request.service;

import org.apache.kafka.streams.kstream.KStream;
import org.mddarr.rides.event.dto.AvroRide;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.util.function.Consumer;

@SpringBootApplication
public class RideRequestApplication {

    public static void main(String[] args) {
        SpringApplication.run(RideRequestApplication.class, args);
    }

    private static final Logger logger = LoggerFactory.getLogger(RideRequestApplication.class);

    @PostConstruct
    public void postInit() {
        logger.info("Application ShowcaseApp started!");
    }

    @Bean
    public Consumer<KStream<String, AvroRide>> process_rides() {
        return (rideRequestStream) -> {
            rideRequestStream.foreach((key, value) -> System.out.println("THE KEY IS AND THE VLAUE IS " + key + " " + value));
        };
    }

}
