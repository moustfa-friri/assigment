package com.nagarro.assignment;

import com.nagarro.assignment.services.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class AssignmentApplicationTests {


    private MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext wac;
    @Autowired
    protected AccountService accountService;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }
    /*****
     * no parameters statement  for admin with user role *
     *******/
    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"USER"})
    public void statementByAccountIdOnlyForAdmin() throws Exception {

        mockMvc
                .perform(get("/statement/search?accountId=3"))
                .andExpect(status().isOk()).andReturn();
    }
    /*****
     * no parameters statement  for user role *
     *******/
    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    public void statementByAccountIdOnlyForUser() throws Exception {

        mockMvc.perform(
                get("/statement/search?accountId=3")
        ).andExpect(status().isOk()).andReturn();
    }

    /*****
     * valid date range for admin *
     *******/
    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    public void statementByAccountIdAndValidDateRangeForAdmin() throws Exception {

        mockMvc.perform(
                get("/statement/search?accountId=3&fromDate=10.10.2010&toDate=10.10.2020")
        ).andExpect(status().isOk()).andReturn();
    }
    /*****
     * invalid date range for admin *
     *******/
    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    public void statementByAccountIdAndInValidDateRangeForAdmin() throws Exception {

        mockMvc.perform(
                get("/statement/search?accountId=3&fromDate=10102010&toDate=10102020")
        ).andExpect(status().isOk()).andReturn();
    }

    /*****
     * valid amount range for admin *
     *******/
    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    public void statementByAccountIdAndValidAmountRangeForAdmin() throws Exception {

        mockMvc.perform(
                get("/statement/search?accountId=3&fromAmount=50&toAmount=200")
        ).andExpect(status().isOk()).andReturn();
    }

    /*****
     * invalid amount range for admin *
     *******/
    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    public void statementByAccountIdAndInValidAmountRangeForAdmin() throws Exception {
        mockMvc.perform(
                get("/statement/search?accountId=3&fromAmount=50&toAmount=invalid")
        ).andExpect(status().isOk()).andReturn();
    }


    /*****
     * parameters amount range for user role *
     *******/
    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    public void statementByAccountIdAndAmountRangeForUser() throws Exception {
        mockMvc.perform(
                get("/statement/search?accountId=3&fromAmount=50&toAmount=invalid")
        ).andExpect(status().isUnauthorized()).andReturn();
    }

    /*****
     * parameters date range for user role *
     *******/
    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    public void statementByAccountIdAndDateRangeForUser() throws Exception {
        mockMvc.perform(
                get("/statement/search?accountId=3&fromDate=10.10.2020&toDate=10.10.2020")
        ).andExpect(status().isUnauthorized()).andReturn();
    }

    /*****
     * default statement page with user role*
     *******/
    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    public void defaultPage() throws Exception {
        mockMvc.perform(
                get("/")
        ).andExpect(status().isOk()).andReturn();
    }

    /*****
     * login page with out authentication*
     *******/
    @Test
    public void loginPage() throws Exception {
        mockMvc.perform(
                get("/login")
        ).andExpect(status().isOk()).andReturn();
    }

}
