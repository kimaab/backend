package me.hello.backend.service;


import me.hello.backend.model.PointTransaction;
import me.hello.backend.repository.PointTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PointTransactionService {

    @Autowired
    private PointTransactionRepository pointTransactionRepository;

    @Transactional
    public void createTransaction(PointTransaction transaction) throws Exception{
        // 한도가 넘었는지 확인
        String resultYn = pointTransactionRepository.checkLimitOver(transaction);
        if(resultYn.equals("Y")){
            throw new Exception("한도초과");
        }
        // 트랜젝션 잠금 시연때 아래 주석 해제 필요
        // String yn = pointTransactionRepository.selectPointTransactionForUpdate(transaction);
        Thread.sleep(1000);
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
