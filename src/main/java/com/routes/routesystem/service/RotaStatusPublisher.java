package com.routes.routesystem.service;

import com.routes.routesystem.model.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class RotaStatusPublisher {

    private static final String TOPIC = "rota-status-topic";

    private final KafkaTemplate<String, Log> kafkaTemplate;

    @Autowired
    public RotaStatusPublisher(KafkaTemplate<String, Log> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishStatusUpdate(Log log) {
        kafkaTemplate.send(TOPIC, log);
    }
}
