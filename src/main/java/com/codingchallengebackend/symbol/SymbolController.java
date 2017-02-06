package com.codingchallengebackend.symbol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SymbolController {

    private SymbolService symbolService;

    @Autowired
    public SymbolController(SymbolService symbolService) {
        this.symbolService = symbolService;
    }

    @RequestMapping("/symbols")
    public Map<String, String> getSymbols() {
        return symbolService.getSymbols();
    }

}