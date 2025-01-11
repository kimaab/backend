package me.hello.backend.service;

import me.hello.backend.member.controller.MemberController;
import me.hello.backend.model.PointLimit;
import me.hello.backend.repository.PointLimitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PointLimitService {
    private static final Logger log = LoggerFactory.getLogger(PointLimitService.class);


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

    public List<PointLimit> findPointLimits() {
        List<PointLimit> list = new ArrayList<>();
        list = pointLimitRepository.findPointLimits();
        if(list.size()>0){
            log.info("size : " +list.get(0).toString());
        }else{
            log.info("size : " +list.size());
        }

        return list;
    }
}
