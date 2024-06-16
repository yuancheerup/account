package com.account.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Diary {
    private Integer id;
    private String title;
    private String cover;
    private String content;
    private Integer userId;
    private String username;
    private LocalDateTime createTime;
}
