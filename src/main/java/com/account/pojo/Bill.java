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
public class Bill {
    private Integer id;
    private String category;
    private String type;
    private BigDecimal money;
    private String wayType;
    private LocalDateTime createTime;
    private Integer userId;
    private String remark;
    private Integer bookId;

    private String username;
    private LocalDate start;
    private LocalDate end;
    private Integer percent;
}
