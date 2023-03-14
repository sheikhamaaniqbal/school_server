package com.school.Complete.Structure.of.a.School.Services;

import com.school.Complete.Structure.of.a.School.Exception.ObjectNotFoundException;
import com.school.Complete.Structure.of.a.School.Entity.CourseMaterial;
import com.school.Complete.Structure.of.a.School.Kafka.Config.KafkaProducers.CourseMaterialProducer;
import com.school.Complete.Structure.of.a.School.Repository.CourseMaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseMaterialServiceImpl implements CourseMaterialService{

    @Autowired
    private CourseMaterialProducer courseMaterialProducer;

    @Autowired
    private CourseMaterialRepository courseMaterialRepository;

    @Override
    public void addCourseMaterial(CourseMaterial courseMaterial) {
        courseMaterialProducer.sendMessage(courseMaterial);
    }

    @Override
    public List<CourseMaterial> fetchAllCourseMaterials() {
        return courseMaterialRepository.findAll();
    }

    @Override
    public void deleteCourseMaterialById(Long id) throws ObjectNotFoundException {
        Optional<CourseMaterial> request_courseMaterial = courseMaterialRepository.findById(id);
        if(request_courseMaterial.isPresent()){
            courseMaterialRepository.deleteById(id);
        }
        else {
            throw new ObjectNotFoundException("No course material with ID: " + id);
        }
    }

    @Override
    public void updateCourseMaterialById(Long id, CourseMaterial courseMaterial) throws ObjectNotFoundException {
        Optional<CourseMaterial> request_courseMaterial = courseMaterialRepository.findById(id);
        if(request_courseMaterial.isPresent()){
            CourseMaterial cm = request_courseMaterial.get();
            if(courseMaterial.getUrl() != null){cm.setUrl(courseMaterial.getUrl());}
            if(courseMaterial.getModules() != null){cm.setModules(courseMaterial.getModules());}
            courseMaterialProducer.sendMessage(cm);
        }
        else{
            throw new ObjectNotFoundException("No course material with ID: " + id);
        }

    }

    @Override
    public CourseMaterial fetchCourseMaterialById(Long id) throws ObjectNotFoundException {
        Optional<CourseMaterial> request_courseMaterial = courseMaterialRepository.findById(id);
        if(request_courseMaterial.isPresent()){
            return request_courseMaterial.get();
        }
        else {
            throw new ObjectNotFoundException("No course material with ID: " + id);
        }

    }
}
