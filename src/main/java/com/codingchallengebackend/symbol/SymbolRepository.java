package com.codingchallengebackend.symbol;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

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

    String getHistoricalSymbolData(String symbol) {
        final String uri = "https://www.quandl.com/api/v3/datatables/WIKI/PRICES.json?ticker=" + symbol +
                "&qopts.columns=date,open,high,low,close&api_key=LATbUEJGyyrRvgwNsEzq";

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri, String.class);
    }

    public static void saveSymbols(Map<String, String> symbols) {
        SymbolRepository.symbols = symbols;
    }
}
