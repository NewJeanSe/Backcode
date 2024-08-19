package Newjeanse.summer.service;

import Newjeanse.summer.domain.Districts;
import Newjeanse.summer.repository.DistrictsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DistrictsService {
    @Autowired
    private DistrictsRepository districtsRepository;

    public void save(Districts districts){
        districtsRepository.save(districts);
    }

    public List<Districts> districtsList(String userId){
        return districtsRepository.findByUserId(userId);
    }

    public void changeDate(Districts districts){
        Districts deleteDistricts = districtsRepository.findData(districts.getUserId(), districts.getName());
        districtsRepository.delete(deleteDistricts);

        districtsRepository.save(districts);
    }

    public void deleteDistricts(String userId, String name){
        Districts deleteDistricts = districtsRepository.findData(userId, name);
        districtsRepository.delete(deleteDistricts);
    }

}
