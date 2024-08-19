package Newjeanse.summer.controller;


import Newjeanse.summer.domain.Bills;
import Newjeanse.summer.domain.Districts;
import Newjeanse.summer.service.BillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bills")
public class BillsController {

    @Autowired
    private BillsService billsService;

    // 전기 요금 데이터베이스 저장
    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody Bills bills){
        try {
            billsService.save(bills);
            return ResponseEntity.ok("success");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail: " + e.getMessage());
        }
    }

    // 전기 요금 데이터베이스 리스트
    @PostMapping("list")
    public ResponseEntity<List<Bills>> districtsList(@RequestBody Map<String, String> request){
        String userId = request.get("userId");

        List<Bills> billsList = billsService.billsList(userId);

        return ResponseEntity.ok(billsList);
    }

    // 전기 요금 데이터베이스 변경
    @PostMapping("change")
    public ResponseEntity<?> change(@RequestBody Bills bills){
        try{
            billsService.changeDate(bills);
            return ResponseEntity.ok("success");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail: " + e.getMessage());
        }
    }
}
