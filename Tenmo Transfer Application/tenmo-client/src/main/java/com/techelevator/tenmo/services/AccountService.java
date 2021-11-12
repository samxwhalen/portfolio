package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.math.BigDecimal;


public class AccountService {

    private String BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser currentUser;

    public AccountService(String url, AuthenticatedUser currentUser) {
        this.currentUser = currentUser;
        BASE_URL = url;
    }

    public BigDecimal getBalance() {
        BigDecimal balance = null;
        try {
           balance = restTemplate.exchange(BASE_URL + "account/balance", HttpMethod.GET, makeAuthEntity(), BigDecimal.class).getBody();

        } catch (RestClientException e) {
            System.out.println("Error retrieving balance");
        }
        return balance;
    }

    private HttpEntity makeAuthEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(currentUser.getToken());
        HttpEntity entity = new HttpEntity<>(headers);
        return entity;
    }

}
