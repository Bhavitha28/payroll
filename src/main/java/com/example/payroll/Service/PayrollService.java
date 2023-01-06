package com.example.payroll.Service;

import com.example.payroll.Entity.Payslip;
import com.example.payroll.repository.PayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayrollService {

    @Autowired
    private PayrollRepository payrollRepo;

    public List<Payslip> saveOrUpdate(List<Payslip> payslips){
        try {
            payrollRepo.saveAll(payslips);
        }catch(Exception e){
            System.out.println(e);

        }
         return payslips;
    }

    public List<Payslip> getPayrolls(List<Long> workdayIds){
        return payrollRepo.findAllByEmpWorkIdIn(workdayIds);
    }

}
