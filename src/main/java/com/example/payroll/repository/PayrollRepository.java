package com.example.payroll.repository;

import com.example.payroll.Entity.Payslip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayrollRepository extends CrudRepository<Payslip,Long> {

    List<Payslip> findAllByEmpWorkIdIn(List<Long> workdayIds);
}
