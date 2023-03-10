package com.interswitch.paytransact.controllers;

import com.interswitch.paytransact.dtos.AccountDto;
import com.interswitch.paytransact.entities.History;
import com.interswitch.paytransact.entities.commons.ApiResponse;
import com.interswitch.paytransact.services.interfaces.AccountService;
import com.interswitch.paytransact.services.interfaces.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private HistoryService historyService;

    @GetMapping("")
    ResponseEntity<ApiResponse> getAccount(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(new ApiResponse(accountService.getAccount(accountDto), "account details fetched successfully"), HttpStatus.OK);
    }

    @PostMapping("/create")
    ResponseEntity<ApiResponse> createAccount(@RequestBody AccountDto accountDto) {
        accountService.createNewAccount(accountDto);
        return new ResponseEntity<>(new ApiResponse("account created successfully"), HttpStatus.OK);
    }

    @GetMapping("/history")
    ResponseEntity<ApiResponse> getAccountHistory() {
        List<History> historyList = historyService.getAccountHistory();
        return new ResponseEntity<>(new ApiResponse(historyList, "Fetched list of account's history"), HttpStatus.OK);
    }
}
