package com.zerobase.mission01.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class History {
    private int idx;
    private Double lat;
    private Double lnt;
    private String regDateTime;
}
