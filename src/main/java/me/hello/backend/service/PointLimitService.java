package me.hello.backend.service;

import me.hello.backend.model.PointLimit;
import me.hello.backend.repository.PointLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointLimitService {

    @Autowired
    private PointLimitRepository pointLimitRepository;

    public void createPointLimit(PointLimit pointLimit) {
        pointLimitRepository.insertPointLimit(pointLimit);
    }

    public PointLimit getPointLimitById(int id) {
        return pointLimitRepository.findPointLimitById(id);
    }

    public List<PointLimit> getPointLimitsByUserId(String userId) {
        return pointLimitRepository.findPointLimitsByUserId(userId);
    }

    public void updatePointLimit(int id, PointLimit pointLimit) {
        pointLimitRepository.updatePointLimit(id, pointLimit);
    }

    public void deletePointLimit(int id) {
        pointLimitRepository.deletePointLimit(id);
    }
}
