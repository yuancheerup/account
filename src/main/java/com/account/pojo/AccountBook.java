package com.account.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountBook {
    private Integer id;
    private String name;
    private String cover;
    private Integer userId;
    private String remark;
    private LocalDateTime createTime;
}
