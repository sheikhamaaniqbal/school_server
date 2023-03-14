package com.school.Complete.Structure.of.a.School.Kafka.Config.KafkaProducers;

import com.school.Complete.Structure.of.a.School.Entity.CourseMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class CourseMaterialProducer {

    @Autowired
    private KafkaTemplate<String, CourseMaterial> kafkaTemplate;

    public void CourseMaterialProducer(KafkaTemplate<String, CourseMaterial> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(CourseMaterial courseMaterial){
        Message<CourseMaterial> cm = MessageBuilder.withPayload(courseMaterial).setHeader(KafkaHeaders.TOPIC, "course_material").build();
        kafkaTemplate.send(cm);
    }
}
