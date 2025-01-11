package me.hello.backend.service;


import me.hello.backend.model.PointTransaction;
import me.hello.backend.repository.PointTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointTransactionService {

    @Autowired
    private PointTransactionRepository pointTransactionRepository;

    public void createTransaction(PointTransaction transaction) {
        pointTransactionRepository.insertTransaction(transaction);
    }

    public List<PointTransaction> getTransactionById() {
        return pointTransactionRepository.findTransaction();
    }

    public PointTransaction getTransactionById(int id) {
        return pointTransactionRepository.findTransactionById(id);
    }

    public List<PointTransaction> getTransactionsByUserId(String userId) {
        return pointTransactionRepository.findTransactionsByUserId(userId);
    }
}
