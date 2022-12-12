package com.assessment.test.model.v1;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class PointResponse {
    private Map<String,Integer> pointEachMonths;
    private Integer totalPointEarned;
}
