package com.pmu.race.registry.event;


import com.pmu.race.registry.model.dto.RaceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventProducer {

    private final KafkaTemplate<String, RaceDto> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private String kafkaTopicName;

    public void sendEvent(RaceDto raceEvent) {
        kafkaTemplate.send(kafkaTopicName, raceEvent);
    }
}

