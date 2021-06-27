package com.nagarro.assignment.controllers;

import com.nagarro.assignment.security.CheckAccess;
import com.nagarro.assignment.services.AccountService;
import com.nagarro.assignment.services.StatementService;
import com.nagarro.assignment.utils.Log4jProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("statement")
public class StatementController {

    @Autowired
    private StatementService statementService;
    @Autowired
    private AccountService accountService;
    private static final String statementPage = "statement";

    @GetMapping(value = "/search", params = {"accountId", "fromAmount", "toAmount"})
    public String statementAmountRange(@RequestParam(value = "accountId", required = true) int accountId,
                                       @RequestParam(value = "fromAmount", required = true, defaultValue = "0") String from,
                                       @RequestParam(value = "toAmount", required = true, defaultValue = "0") String to, Model model) {

        double fromAmount;
        double toAmount;
        CheckAccess.check("ROLE_ADMIN");
        model.addAttribute("accounts", accountService.findAll());
        try {
            fromAmount = Double.valueOf(from);
            toAmount = Double.valueOf(to);
            model.addAttribute("statements", statementService.getAmountBetween(accountId, fromAmount, toAmount));
            Log4jProxy.logger.info("amount filter account id " + accountId + " range from " + fromAmount + " to " + toAmount);

        } catch (Exception e) {
            model.addAttribute("msg", "invalid amount Range");
            Log4jProxy.logger.error("invalid amount account id " + accountId + " range from " + from + " to " + to);

        }
        return statementPage;


    }

    @GetMapping(value = "/search", params = {"accountId", "fromDate", "toDate"})
    public String statementDateRange(@RequestParam(value = "accountId", required = true) int accountId,
                                     @RequestParam(value = "fromDate", required = true) String fromDate,
                                     @RequestParam(value = "toDate", required = true) String toDate, Model model) {
        CheckAccess.check("ROLE_ADMIN");
        model.addAttribute("accounts", accountService.findAll());
        try {
            model.addAttribute("statements", statementService.getDateBetween(accountId, fromDate, toDate));
            Log4jProxy.logger.info("date filter account id " + accountId + " range from " + fromDate + " to " + toDate);

        } catch (Exception exception) {
            model.addAttribute("msg", exception.getMessage());
            Log4jProxy.logger.error("invalid date range account id " + accountId + " range from " + fromDate + " to " + toDate);

        }
        return statementPage;
    }


    @GetMapping(value = "/search", params = {"accountId"})
    public String statementLastThreeMonth(@RequestParam(value = "accountId", required = true) int accountId, Model model) {

        CheckAccess.check("ROLE_USER");
        model.addAttribute("accounts", accountService.findAll());
        try {
            model.addAttribute("statements", statementService.getLastThreeMonthMovement(accountId));
            Log4jProxy.logger.info("no filter request account id " + accountId);
        } catch (Exception exception) {
            model.addAttribute("msg", "fetal error");
            Log4jProxy.logger.error("no filter request  account id " + accountId + " " + exception.getMessage());

        }
        return statementPage;
    }


}
