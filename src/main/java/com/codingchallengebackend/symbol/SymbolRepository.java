package com.codingchallengebackend.symbol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class SymbolRepository {

    private static Map<String, String> symbols;

    @Autowired
    public Map<String, String> getSymbols() {
        return symbols;
    }

    public static void saveSymbols(Map<String, String> symbols) {
        SymbolRepository.symbols = symbols;
    }
}
