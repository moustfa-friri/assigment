package com.nagarro.assignment.security;

import com.nagarro.assignment.controllers.StatementController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.server.ResponseStatusException;

public class CheckAccess {
    static Logger logger = LoggerFactory.getLogger(StatementController.class);

    public static void check(String role) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(role))) {
            logger.error("unauthorized access for user " + auth.getName());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Ops unauthorized user");

        } else
            logger.info("success use privilege  " + auth.getName() + " role " + role);

    }
}
