package com.account.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
    private Integer id;
    private String category;
    private String type;
    private Double money;
    private String wayType;
    private LocalDateTime createTime;
    private Integer userId;
    private String remark;

    private String username;
    private LocalDate start;
    private LocalDate end;
}
