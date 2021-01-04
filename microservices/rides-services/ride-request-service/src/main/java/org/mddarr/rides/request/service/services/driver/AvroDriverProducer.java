package org.mddarr.rides.request.service.services.driver;

import org.mddarr.rides.event.dto.AvroDriver;
import org.mddarr.rides.event.dto.AvroRideRequest;
import org.mddarr.rides.request.service.Constants;
import org.mddarr.rides.request.service.models.DriverRequest;
import org.mddarr.rides.request.service.services.riderequest.AvroRideRequestProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AvroDriverProducer implements AvroDriverProducerInterface{

    @Autowired
    private KafkaTemplate<String, AvroDriver> kafkaDriverTemplate;

    private static final Logger logger = LoggerFactory.getLogger(AvroRideRequestProducer.class);

    @Override
    public String activateDriver(DriverRequest driverRequest) {
        System.out.println("THE DRIVER REQUEST LOOKS LIKE " + driverRequest);
        AvroDriver avroDriver = AvroDriver.newBuilder()
                .setFirstname(driverRequest.getDriver_first_name())
                .setLastname(driverRequest.getDriver_last_name())
                .setDriverid(driverRequest.getDriverid())
                .build();
        kafkaDriverTemplate.setDefaultTopic(Constants.DRIVERS_TOPIC);
        kafkaDriverTemplate.sendDefault("Seattle", avroDriver);
//        kafkaDriverTemplate.send(Constants.DRIVERS_TOPIC, avroDriver);
        return driverRequest.getDriverid();
    }
}
