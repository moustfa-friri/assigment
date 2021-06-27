package com.nagarro.assignment.services;

import com.nagarro.assignment.dao.StatementRepository;
import com.nagarro.assignment.models.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatementService {

    @Autowired
    private StatementRepository statementRepository;


    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public List<Statement> getDateBetween(int accountId, String from, String to) throws Exception {
        Date startDate = formatDate(from), toDate = formatDate(to);
        return statementRepository.findByAccountId(accountId).stream().filter(statement -> statement.getDateField().compareTo(startDate) >= 0 && statement.getDateField().compareTo(toDate) <= 0).collect(Collectors.toList());
    }

    public List<Statement> getAmountBetween(int accountId, double fromAmount, double toAmount)  {
        return statementRepository.findByAccountId(accountId).stream().filter(statement -> statement.getAmount() >= fromAmount && statement.getAmount() <= toAmount).collect(Collectors.toList());
    }

    public List<Statement> getLastThreeMonthMovement(int accountId) {

        Date toDay = new Date();
        Calendar beforeThreeMonthCalendar = Calendar.getInstance();
        beforeThreeMonthCalendar.add(Calendar.MONTH, -3);
         return statementRepository.findByAccountId(accountId).stream().filter(statement -> beforeThreeMonthCalendar.getTime().compareTo(statement.getDateField()) <= 0).collect(Collectors.toList());


    }


    private Date formatDate(String date) throws Exception {
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            throw new Exception ("invalid date range");

        }
    }
}
