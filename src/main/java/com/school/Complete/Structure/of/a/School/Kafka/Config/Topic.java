package com.school.Complete.Structure.of.a.School.Kafka.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class Topic {

    @Bean
    public NewTopic courseMaterialTopic(){
        return TopicBuilder.name("course_material").build();
    }

}
