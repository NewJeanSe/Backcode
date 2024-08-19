package Newjeanse.summer.controller;

import Newjeanse.summer.configuration.SessionConst;
import Newjeanse.summer.domain.Districts;
import Newjeanse.summer.domain.User;
import Newjeanse.summer.service.DistrictsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/districts")
public class DistrictsController {

    @Autowired
    private DistrictsService districtsService;

    // 시/군/구 데이터베이스 저장
    @PostMapping("save")
    public ResponseEntity<?> save(@RequestBody Map<String, String> name){
        String userId = name.get("userId");
        String regionName = name.get("name");

        Districts districts = new Districts();
        districts.setName(regionName);
        districts.setUserId(userId);
        System.out.println(districts.getName() + " " + districts.getUserId());
        try {
            districtsService.save(districts);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail: " + e.getMessage());
        }
    }

    // 시/군/구 데이터베이스 리스트
    @PostMapping("{userId}")
    public ResponseEntity<List<Districts>> districtsList(@PathVariable String userId){
        List<Districts> districtsList = districtsService.districtsList(userId);

        return ResponseEntity.ok(districtsList);
    }

    // 시/군/구 데이터베이스 변경
    @PostMapping("change")
    public ResponseEntity<?> change(@RequestBody Districts districts){
        try{
            districtsService.changeDate(districts);
            return ResponseEntity.ok("success");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail: " + e.getMessage());
        }
    }

    // 시/군/구 데이터베이스 삭제
    @PostMapping("remove")
    public ResponseEntity<?> removeDistricts(@RequestBody Map<String, String> name){
        String userId = name.get("userId");
        String regionName = name.get("name");

        try {
            districtsService.deleteDistricts(userId, regionName);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail: " + e.getMessage());
        }
    }
}
