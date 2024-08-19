package Newjeanse.summer.service;


import Newjeanse.summer.domain.Bills;
import Newjeanse.summer.repository.BillsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BillsService {

    @Autowired
    private BillsRepository billsRepository;

    public void save(Bills bills){
        billsRepository.save(bills);
    }

    public List<Bills> billsList(String userId){
        return billsRepository.findByUserId(userId);
    }

    public void changeDate(Bills bills){
        Bills deleteBills = billsRepository.changeDate(bills.getUserId(), bills.getName());
        billsRepository.delete(deleteBills);

        billsRepository.save(bills);
    }
}
