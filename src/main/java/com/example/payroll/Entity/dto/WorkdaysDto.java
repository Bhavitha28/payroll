package com.example.payroll.Entity.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WorkdaysDto {
    private List<WorkdayDto> workDays = new ArrayList<>();

}
