package com.codingchallengebackend.symbol;

import com.codingchallengebackend.H2DatabaseService;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class SymbolRepository {

    List<Symbol> getSymbols(String searchTerm) {
        Map<String, String> symbolMapping = H2DatabaseService.selectWithPreparedStatement();
        List<Symbol> symbolList = new ArrayList<>();

        addToSymbolList(symbolMapping, symbolList, searchTerm);

        return symbolList;
    }

    QuandlData getHistoricalSymbolData(String symbol) {
        String uri = "https://www.quandl.com/api/v3/datatables/WIKI/PRICES.json?ticker={symbol}" +
                "&date.gte={date}&qopts.columns=date,adj_open,adj_high,adj_low,adj_close&api_key=LATbUEJGyyrRvgwNsEzq";
        Map<String, String> params = new HashMap<>();
        params.put("symbol", symbol);
        params.put("date", getStartDate());
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(uri);

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(builder.buildAndExpand(params).toUri(), QuandlData.class);
    }

    private void addToSymbolList(Map<String, String> symbolMapping, List<Symbol> symbolList, String searchTerm) {
        int id = 1;
        for (Map.Entry<String, String> symbolEntry : symbolMapping.entrySet()) {
            String symbol = symbolEntry.getKey();
            String name = symbolEntry.getValue();
            if (symbol.toLowerCase().contains(searchTerm.toLowerCase()) ||
                    name.toLowerCase().contains(searchTerm.toLowerCase())) {
                Symbol symbolObject = new Symbol(id, symbol, name);
                symbolList.add(symbolObject);
                id++;
            }
        }
    }

    private String getStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -14000);
        Long dateInMilliseconds = calendar.getTimeInMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date(dateInMilliseconds));
    }
}
