package com.itxiaole.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class ClazzsQueryParam {
    private Integer page = 1;
    private Integer pageSize = 5;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-DD")
    private LocalDate begin;
    @DateTimeFormat(pattern = "yyyy-MM-DD")
    private LocalDate end;
}
