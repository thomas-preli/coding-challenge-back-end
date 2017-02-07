package com.codingchallengebackend.symbol;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SymbolRepository {

    private static Map<String, String> symbols;

    List<Symbol> getSymbols(String searchTerm) {
        List<Symbol> symbolList = new ArrayList<>();
        int id = 1;
        for (Map.Entry<String, String> symbol : symbols.entrySet()) {
            if (symbol.getKey().toLowerCase().contains(searchTerm.toLowerCase()) ||
                    symbol.getValue().toLowerCase().contains(searchTerm.toLowerCase())) {
                Symbol symbolObject = new Symbol(id, symbol.getKey(), symbol.getValue());
                symbolList.add(symbolObject);
                id++;
            }
        }
        return symbolList;
    }

    public static void saveSymbols(Map<String, String> symbols) {
        SymbolRepository.symbols = symbols;
    }
}
