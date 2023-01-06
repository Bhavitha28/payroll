package com.example.payroll.controller;

import com.example.payroll.Entity.dto.PayslipDto;
import com.example.payroll.Entity.dto.PayslipsDto;
import com.example.payroll.Entity.dto.WorkdaysDto;
import com.example.payroll.Entity.Payslip;
import com.example.payroll.Service.PayrollService;
import com.example.payroll.utils.Payslipconstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin("*")
@RequestMapping("/admin")
@RestController
public class PayrollController {

    @Autowired
    private PayrollService payrollService;


    @PostMapping("/generate-payroll")
    public void generatepayroll(@RequestBody WorkdaysDto workdays){
        List<Payslip> payslips = new ArrayList<>();
        workdays.getWorkDays().forEach(workday->{
            Payslip pays = new Payslip();
            Double salary = workday.getSalary();
            Double basicPay=salary*(Payslipconstants.BASIC/100) ;
            Double HRA=salary*(Payslipconstants.HRA/100) ;
            Double taxDeduction = salary*(Payslipconstants.TAX/100) ;
//            Double epfDeduction = salary* (Payslipconstants.EPF/100);
//            Double hisDeduction = salary* (Payslipconstants.HIS/100);

            Double leaveDeduction = (salary/30) * ((workday.getLeaveBal() - workday.getLeavesApplied()) < 0 ? (workday.getLeavesApplied() - workday.getLeaveBal()) : 0.0) ;
            Double netSalary = salary - taxDeduction - Payslipconstants.EPF - Payslipconstants.HIS - leaveDeduction;
            pays.setEmpWorkId(workday.getId());
            pays.setTimeFrom(workday.getTimeFrom());
            pays.setTimeTo(workday.getTimeTo());
            pays.setBasic(basicPay.floatValue());
            pays.setHra(HRA.floatValue());
            pays.setHis(Payslipconstants.HIS );
            pays.setNetpay(netSalary.floatValue());
            pays.setTax(taxDeduction.floatValue());
            pays.setEpf(Payslipconstants.EPF);
            pays.setLeaveDeduction(leaveDeduction.floatValue());
            payslips.add(pays);
        });
        payrollService.saveOrUpdate(payslips);
    }

    @PostMapping("/payrolls")
    public PayslipsDto getPayrolls(@RequestBody WorkdaysDto workdays){
        List<Long> empWorkdayIds = new ArrayList<>();
        workdays.getWorkDays().forEach(workday->{
            empWorkdayIds.add(workday.getId());
        });
        List<Payslip> payslips = payrollService.getPayrolls(empWorkdayIds);
        PayslipsDto payslipsDto = new PayslipsDto();
        payslips.forEach((payslip) -> {
            PayslipDto payslipDto = new PayslipDto();
            payslipDto.setNetpay(payslip.getNetpay());
            payslipDto.setTax(payslip.getTax());
            payslipDto.setEpf(payslip.getEpf());
            payslipDto.setHis(payslip.getHis());
            payslipDto.setBasic(payslip.getBasic());
            payslipDto.setHra(payslip.getHra());
            payslipDto.setLeaveDeduction(payslip.getLeaveDeduction());
            payslipDto.setEmpWorkId(payslip.getEmpWorkId());
            payslipsDto.getPayslips().add(payslipDto);
        });
        return payslipsDto;
    }
}
