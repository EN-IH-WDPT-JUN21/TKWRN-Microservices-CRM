package com.ironhack.ReportService.Service;

import com.ironhack.ReportService.repository.AccountRepository;
import com.ironhack.ReportService.repository.OpportunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QueryHelperService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    OpportunityRepository opportunityRepository;

    public Double findMedianEmployeeCount() {
        Optional<int[]> employeeCounts = accountRepository.findMedianEmployeeCountStep1();
        if(employeeCounts.isEmpty()){
            return null;
        } else {
            int[] foundEmployeeCounts = employeeCounts.get();
            Double midPoint = Math.floor(employeeCounts.get().length / 2);
            if(foundEmployeeCounts.length % 2 == 0){
                return Double.valueOf(foundEmployeeCounts[(int) Math.round(midPoint)]);
            } else {
                return Double.valueOf(foundEmployeeCounts[(int) Math.round(midPoint) - 1] + foundEmployeeCounts[(int) Math.round(midPoint) + 1] / 2);
            }
        }
    }

    public Double findMedianQuantity() {
        Optional<int[]> quantities = opportunityRepository.findMedianQuantityStep1();
        if(quantities.isEmpty()){
            return null;
        } else {
            int[] foundQuantities = quantities.get();
            Double midPoint = Math.floor(foundQuantities.length / 2);
            if(foundQuantities.length % 2 == 0){
                return Double.valueOf(foundQuantities[(int) Math.round(midPoint)]);
            } else {
                return Double.valueOf(foundQuantities[(int) Math.round(midPoint) - 1] + foundQuantities[(int) Math.round(midPoint) + 1] / 2);
            }
        }
    }

    public Double findMedianOppsPerAccount() {
        Optional<int[]> opps = opportunityRepository.findMedianOppsPerAccountStep1();
        if(opps.isEmpty()){
            return null;
        } else {
            int[] foundOpps = opps.get();
            Double midPoint = Math.floor(foundOpps.length / 2);
            if(foundOpps.length % 2 == 0){
                return Double.valueOf(foundOpps[(int) Math.round(midPoint)]);
            } else {
                return Double.valueOf(foundOpps[(int) Math.round(midPoint) - 1] + foundOpps[(int) Math.round(midPoint) + 1] / 2);
            }
        }
    }
}
