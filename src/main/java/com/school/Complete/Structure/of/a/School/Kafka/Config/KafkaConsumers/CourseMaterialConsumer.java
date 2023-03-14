package com.school.Complete.Structure.of.a.School.Kafka.Config.KafkaConsumers;

import com.school.Complete.Structure.of.a.School.Entity.CourseMaterial;
import com.school.Complete.Structure.of.a.School.Repository.CourseMaterialRepository;
import com.school.Complete.Structure.of.a.School.Services.CourseMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CourseMaterialConsumer {

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @KafkaListener(
            topics = "course_material",
            groupId = "school"
    )
    public void addCourseMaterial(CourseMaterial courseMaterial){
        courseMaterialRepository.save(courseMaterial);
    }
}
