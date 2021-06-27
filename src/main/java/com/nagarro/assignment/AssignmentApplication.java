package com.nagarro.assignment;

import com.nagarro.assignment.models.Statement;
import com.nagarro.assignment.services.AccountService;
import com.nagarro.assignment.services.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@SpringBootApplication
public class AssignmentApplication  {

    public static void main(String[] args) {
        SpringApplication.run(AssignmentApplication.class, args);

    }

}
