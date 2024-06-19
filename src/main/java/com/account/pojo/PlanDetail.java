package com.account.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanDetail {
    private Integer id;
    private Integer planId;
    private LocalDateTime date;
    private BigDecimal money;
    private BigDecimal total;
}
