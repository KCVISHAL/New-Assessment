package com.assessment.test.services.v1.impl;

import com.assessment.test.model.v1.TransactionDetails;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;


@RunWith(MockitoJUnitRunner.class)
public class GetPointServiceImplementationTest {
    @InjectMocks
    GetPointServiceImplementation underTest;

    @Test
    public void testGetPoints(){
        Assert.assertNotNull(underTest.getPoints());
    }

    @Test
    public void testGetPointForEachDate_zeroPoint(){
        TransactionDetails transactionDetails = new TransactionDetails();
        transactionDetails.setTransactionDate(LocalDateTime.now());
        transactionDetails.setTransAmount(12.23);
        transactionDetails.setTransId(1L);
        Map<LocalDate, Integer> actual = underTest.getPointForEachDate(Arrays.asList(transactionDetails));
        Assert.assertEquals(Integer.valueOf(0),actual.get(LocalDate.now()));
    }
    @Test
    public void testGetPointForEachDate_SomePoint(){
        TransactionDetails transactionDetails = new TransactionDetails();
        transactionDetails.setTransactionDate(LocalDateTime.now());
        transactionDetails.setTransAmount(51.00);
        transactionDetails.setTransId(1L);
        Map<LocalDate, Integer> actual = underTest.getPointForEachDate(Arrays.asList(transactionDetails));
        Assert.assertEquals(Integer.valueOf(1),actual.get(LocalDate.now()));
    }

    @Test
    public void testGetPointsFromTransaction_zeroPoint() {
        Integer actual = underTest.getPointsFromTransaction(12.22);
        Assert.assertEquals(Integer.valueOf(0),actual);
    }

    @Test
    public void testGetPointsFromTransaction_SomePoint() {
        Integer actual = underTest.getPointsFromTransaction(52.22);
        Assert.assertEquals(Integer.valueOf(2),actual);
    }
}