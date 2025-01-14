package me.hello.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.hello.backend.model.PointTransaction;
import me.hello.backend.service.PointTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/points")
@CrossOrigin(origins = "http://localhost:8080")
public class PointTransactionController {
    @Autowired
    private PointTransactionService pointTransactionService;

    @GetMapping
    @Operation(summary = "모든 사용자 포인트 거래 조회", description = "포인트 거래를 조회합니다.")
    public List<PointTransaction> getTransactionById() {
        return pointTransactionService.getTransactionById();
    }

    @PostMapping
    @Operation(summary = "새 포인트 거래 생성", description = "새 포인트 거래(적립/사용)를 생성합니다.")
    public PointTransaction createTransaction(@RequestBody PointTransaction transaction) throws Exception{
        pointTransactionService.createTransaction(transaction);
        return transaction;
    }

    @GetMapping("/{id}")
    @Operation(summary = "ID로 포인트 거래 조회", description = "고유 ID로 포인트 거래를 조회합니다.")
    public PointTransaction getTransactionById(@PathVariable int id) {
        return pointTransactionService.getTransactionById(id);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "사용자 ID로 포인트 거래 조회", description = "특정 사용자의 모든 포인트 거래를 조회합니다.")
    public List<PointTransaction> getTransactionsByUserId(@PathVariable String userId) {
        return pointTransactionService.getTransactionsByUserId(userId);
    }
}