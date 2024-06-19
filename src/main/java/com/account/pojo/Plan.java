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
public class Plan {
    private Integer id;
    private String name;
    private String cover;
    private BigDecimal money;
    private LocalDate start;
    private LocalDate end;
    private Integer userId;
    private LocalDateTime createTime;

    private Integer percent;
    private String status;
    private String username;
}
