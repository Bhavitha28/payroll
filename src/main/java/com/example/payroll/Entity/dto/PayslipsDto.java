package com.example.payroll.Entity.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PayslipsDto {
    private List<PayslipDto> payslips = new ArrayList<>();
}
