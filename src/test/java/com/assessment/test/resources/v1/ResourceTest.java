package com.assessment.test.resources.v1;

import com.assessment.test.model.v1.PointResponse;
import com.assessment.test.services.v1.api.GetPointsServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class ResourceTest {
    @Mock
    GetPointsServices getPointsServices;
    @InjectMocks
    Resource underTest;
    PointResponse expected;
    @Before
    public void setUp() {
        expected = new PointResponse();
        Map<String,Integer> resp = new HashMap<>();
        resp.put("2022-MAY",230);
        expected.setPointEachMonths(resp);
        expected.setTotalPointEarned(230);
        Mockito.when(getPointsServices.getPoints()).thenReturn(expected);
    }

    @Test
    public void testGetPoint_success() {
        ResponseEntity<Object> actual = underTest.getPoint();
        Assert.assertEquals(expected,(PointResponse)actual.getBody());
    }

    @Test
    public void testGetPoint_failure() {
        Mockito.when(getPointsServices.getPoints()).thenThrow(NullPointerException.class);
        ResponseEntity<Object> actual = underTest.getPoint();
        Assert.assertEquals(HttpStatus.BAD_REQUEST,actual.getStatusCode());
    }
}