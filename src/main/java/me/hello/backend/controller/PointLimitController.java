package me.hello.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.hello.backend.model.PointLimit;
import me.hello.backend.service.PointLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/point-limits")
public class PointLimitController {

    @Autowired
    private PointLimitService pointLimitService;

    @PostMapping
    @Operation(summary = "새 포인트 사용 한도 생성", description = "새 포인트 사용 한도를 생성합니다.")
    public PointLimit createPointLimit(@RequestBody PointLimit pointLimit) {
        pointLimitService.createPointLimit(pointLimit);
        return pointLimit;
    }

    @GetMapping("/{id}")
    @Operation(summary = "ID로 포인트 사용 한도 조회", description = "고유 ID로 포인트 사용 한도를 조회합니다.")
    public PointLimit getPointLimitById(@PathVariable int id) {
        return pointLimitService.getPointLimitById(id);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "사용자 ID로 포인트 사용 한도 조회", description = "특정 사용자의 월간 포인트 사용 한도를 조회합니다.")
    public List<PointLimit> getPointLimitsByUserId(@PathVariable String userId) {
        return pointLimitService.getPointLimitsByUserId(userId);
    }

    @PutMapping("/{id}")
    @Operation(summary = "포인트 사용 한도 업데이트", description = "고유 ID로 포인트 사용 한도를 업데이트합니다.")
    public PointLimit updatePointLimit(@PathVariable int id, @RequestBody PointLimit pointLimit) {
        pointLimitService.updatePointLimit(id, pointLimit);
        return pointLimit;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "포인트 사용 한도 삭제", description = "고유 ID로 포인트 사용 한도를 삭제합니다.")
    public void deletePointLimit(@PathVariable int id) {
        pointLimitService.deletePointLimit(id);
    }
}
