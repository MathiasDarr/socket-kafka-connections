package org.mddarr.ridereceiver;

import org.apache.kafka.streams.kstream.*;

import org.mddarr.rides.event.dto.AvroDriver;
import org.mddarr.rides.event.dto.AvroRide;
import org.mddarr.rides.event.dto.AvroRideRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

@SpringBootApplication
public class RideDispatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(RideDispatchApplication.class, args);
	}

	public static final String INPUT_TOPIC = "input";
	public static final String OUTPUT_TOPIC = "output";
	public static final int WINDOW_SIZE_MS = 30_000;


//	@Bean
//	public Function<KStream<String, AvroRideRequest>, KStream<String, AvroRideRequest>>  process_ride_requests() {
//		return (rideRequestStream) -> {
//			rideRequestStream.foreach((key, value) -> System.out.println("THE KEY IS AND THE VLAUE IS " + key + " " + value));
//			return rideRequestStream;
//
//		};
//	}
//
//	@Bean
//	public Function<KStream<String, AvroDriver>, KStream<String, AvroDriver>>  process_drivers() {
//		return (avroDriverKStream) -> {
//			avroDriverKStream.foreach((key, value) -> System.out.println("THE KEY IS AND THE VLAUE IS " + key + " " + value));
//			return avroDriverKStream;
//
//		};
//	}

	@Bean
	public BiConsumer<KStream<String, AvroDriver>, KStream<String, AvroRideRequest>> join_ride_requests(){
		return (avroDriverKStream, rideRequestKStream) -> {
			avroDriverKStream.foreach((key, value) -> System.out.println("THE KEY OF THE DRIVER IS AND THE VLAUE IS " + key + " " + value));
			rideRequestKStream.foreach((key, value) -> System.out.println("THE KEY AND THE VALUE OF THE RIDE REQUEST IS AND THE VLAUE IS " + key + " " + value));
			

		};
	}



}
