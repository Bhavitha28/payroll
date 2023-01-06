package com.example.payroll.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="payslip")
public class Payslip {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="ID")
    private Long id;

    @Column(name="EMP_WORK_ID")
    private Long empWorkId;

    @Column(name="EPF")
    private Float epf;

    @Column(name="TAX")
    private Float tax;

    @Column(name="LEAVE_DEDUCTION")
    private Float leaveDeduction;

    @Column(name="HIS")
    private Float his;

    @Column(name="NETPAY")
    private  Float netpay;

    @Column(name="BASIC")
    private Float basic;

    @Column(name="HRA")
    private Float hra;

    @Column(name="TIME_FROM")
    private Date timeFrom;

    @Column(name="TIME_TO")
    private Date timeTo;

}
