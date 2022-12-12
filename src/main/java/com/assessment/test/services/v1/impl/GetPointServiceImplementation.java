package com.assessment.test.services.v1.impl;


import com.assessment.test.services.v1.api.GetPointsServices;
import com.assessment.test.model.v1.PointResponse;
import com.assessment.test.model.v1.TransactionDetails;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import javax.inject.Named;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Named
public class GetPointServiceImplementation implements GetPointsServices {
    @Override
    public PointResponse getPoints() {
        PointResponse resp = new PointResponse();
        resp.setTotalPointEarned(0);
        resp.setPointEachMonths(new HashMap<>());
        List<TransactionDetails> transactionsList = getTransactionList();
        getPointForEachDate(transactionsList).forEach((x, y) -> {
            resp.getPointEachMonths().merge(x.getYear()+"-"+x.getMonth(),y,Integer::sum);
            resp.setTotalPointEarned(resp.getTotalPointEarned()+y);
        });
        System.out.println(resp);
        return resp;
    }

    public Map<LocalDate, Integer> getPointForEachDate(List<TransactionDetails> transactionsList) {
        Map<LocalDate, Integer> pointsEachDay = new HashMap<>();
        transactionsList.forEach(trn -> {
            pointsEachDay.merge(trn.getTransactionDate().toLocalDate(), getPointsFromTransaction(trn.getTransAmount()), Integer::sum);
        });
        return pointsEachDay;
    }

    public int getPointsFromTransaction(Double amount) {
        if (amount <= 50) return 0;
        double amountToCalcPoint = amount - 50;
        int points = 0;
        for (int i = 1; i <= amountToCalcPoint; i++) {
            if (i <= 50) points += 1;
            else points += 2;
        }
        return points;
    }

    private List<TransactionDetails> getTransactionList() {
        try {
            CSVReader reader =
                    new CSVReaderBuilder(new FileReader("src/main/resources/dataSetup.csv")).
                            withSkipLines(1). // Skiping firstline as it is header
                            build();
            List<TransactionDetails> transactionDetailsList = reader.readAll().stream().map(data -> {
                TransactionDetails transactionDetails = new TransactionDetails();
                transactionDetails.setTransId(Long.valueOf(data[0]));
                transactionDetails.setTransactionDate(LocalDateTime.parse(data[1]));
                transactionDetails.setTransAmount(Double.valueOf(data[2]));
                return transactionDetails;
            }).collect(Collectors.toList());
            return transactionDetailsList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }


}
