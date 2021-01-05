package org.mddarr.producer;

import org.mddarr.producer.kafka.generictemplate.KafkaGenericTemplate;
import org.mddarr.producer.models.Driver;
import org.mddarr.producer.models.DrivingSession;
import org.mddarr.producer.models.RideRequestSession;
import org.mddarr.producer.models.User;
import org.mddarr.producer.repositories.DriverRepository;
import org.mddarr.producer.repositories.UserRepository;
import org.mddarr.producer.services.DataService;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;


import org.mddarr.rides.event.dto.*;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.*;


public class EventProducer {

    public static void main(String[] args) throws Exception {
//        populateDrivers();
        populateSingleRideRequests();
        populateSingleDriver();

    }

    public static void populateSingleRideRequests() throws Exception {

        KafkaGenericTemplate<AvroRideRequest> kafkaGenericTemplate = new KafkaGenericTemplate<>();
        KafkaTemplate<String, AvroRideRequest> rideRequestKafkaTemplate = kafkaGenericTemplate.getKafkaTemplate();
        rideRequestKafkaTemplate.setDefaultTopic(Constants.RIDE_REQUEST_TOPIC);
        AvroRideRequest rideRequest = new AvroRideRequest("requestid1", "user1", 3, "Ballard");
        rideRequestKafkaTemplate.sendDefault(rideRequest);
        System.out.println("Writing ride request for '" + rideRequest.getRequestId() + "' to input topic " + Constants.RIDE_REQUEST_TOPIC);
    }

    public static void populateRides() throws Exception{
        KafkaGenericTemplate<AvroRide> kafkaGenericTemplate = new KafkaGenericTemplate<AvroRide>();
        KafkaTemplate<String, AvroRide> rideRequestKafkaTemplate = kafkaGenericTemplate.getKafkaTemplate();
        rideRequestKafkaTemplate.setDefaultTopic(Constants.RIDES_TOPIC);

        AvroRide avroRide = new AvroRide("ride1","user1","driver1");
        rideRequestKafkaTemplate.sendDefault(avroRide);
        System.out.println("SENT " + avroRide);

    }

    public static void populateSingleDriver(){
        KafkaGenericTemplate<AvroDriver> kafkaGenericTemplate = new KafkaGenericTemplate<>();
        KafkaTemplate<String, AvroDriver> driverKafkaTemplate = kafkaGenericTemplate.getKafkaTemplate();

        driverKafkaTemplate.setDefaultTopic(Constants.DRIVERS_TOPIC);
        String city = "Seattle";

        AvroDriver avroDriver = AvroDriver.newBuilder()
                .setDriverid("driver1")
                .setFirstname("Erik")
                .setLastname("Charles")
                .setState(AvroDriverState.ACTIVE)
                .build();
        System.out.println("Activating driver '" + avroDriver.getFirstname() + "' to input topic " + Constants.DRIVERS_TOPIC);

        driverKafkaTemplate.sendDefault(city, avroDriver);
    }


    public static void populateAllDrivers() throws Exception{

        KafkaGenericTemplate<AvroDriver> kafkaGenericTemplate = new KafkaGenericTemplate<>();
        KafkaTemplate<String, AvroDriver> driverKafkaTemplate = kafkaGenericTemplate.getKafkaTemplate();

        driverKafkaTemplate.setDefaultTopic(Constants.DRIVERS_TOPIC);
        List<Driver> drivers = DriverRepository.getDriversFromDB();

        drivers.forEach(driver -> {
            System.out.println("Writing driver for '" + driver.getFirst_name() + "' to input topic " +
                    Constants.DRIVERS_TOPIC);
            driverKafkaTemplate.sendDefault(new AvroDriver(driver.getDriverid(), driver.getFirst_name(), driver.getLast_name(), AvroDriverState.ACTIVE));
        });
    }

}
