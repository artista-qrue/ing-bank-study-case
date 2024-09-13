package org.ing.ingbankstudycase.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.ing.ingbankstudycase.repository.UserRepository;
import org.ing.ingbankstudycase.security.JwtTokenProvider;
import org.ing.ingbankstudycase.service.CustomerService;
import org.ing.ingbankstudycase.service.impl.UserDetailsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Author: Ahmet Dellal User:adellal Date:13.09.2024 Time:03:16
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Test
    public void testGetCustomer() throws Exception {
        mockMvc.perform(get("/customers/1").header("Authorization", "Bearer " + "USED_VALID_A_TOKEN"))
            .andExpect(status().isOk());
    }

    @Test
    public void testGetCustomers() throws Exception {
        mockMvc.perform(get("/customers").header("Authorization", "Bearer " + "USED_VALID_A_TOKEN"))
            .andExpect(status().isOk());
    }

}
