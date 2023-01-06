package com.example.payroll.Entity.dto;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class PayslipDto {
    private Long id;

    private Long empWorkId;

    private Float epf;

    private Float tax;

    private Float leaveDeduction;

    private Float his;

    private  Float netpay;

    private Float hra;

    private Float basic;

    private Date timeFrom;

    private Date timeTo;

}
