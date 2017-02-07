package com.codingchallengebackend.symbol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SymbolController {

    private SymbolService symbolService;

    @Autowired
    public SymbolController(SymbolService symbolService) {
        this.symbolService = symbolService;
    }

    @CrossOrigin
    @RequestMapping(value = "/symbols", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<Symbol>> getSymbols(@RequestParam("searchTerm") String searchTerm) {
        Map<String, List<Symbol>> result = new HashMap<>();
        result.put("symbols", symbolService.getSymbols(searchTerm));
        return result;
    }

}