package com.codingchallengebackend.symbol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class SymbolService {

    private SymbolRepository symbolRepository;

    @Autowired
    public SymbolService(SymbolRepository symbolRepository) {
        this.symbolRepository = symbolRepository;
    }

    List<Symbol> getSymbols(String searchTerm) {
        return symbolRepository.getSymbols(searchTerm);
    }

    List<List<Object>> getHistoricalSymbolData(String symbol) {
        QuandlData quandlData = symbolRepository.getHistoricalSymbolData(symbol);
        return formatHistoricalSymbolData(quandlData);
    }

    private List<List<Object>> formatHistoricalSymbolData(QuandlData quandlData) {
        DataTableData dataTableData = quandlData.getDatatable();
        List<List<Object>> data = dataTableData.getData();
        for (List<Object> row : data) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            NumberFormat formatter = new DecimalFormat("#0.00");
            try {
                Date date = sdf.parse(row.get(0).toString());
                long dateInMilliseconds = date.getTime();
                row.set(0, dateInMilliseconds);
                row.set(1, Double.parseDouble(formatter.format(row.get(1))));
                row.set(2, Double.parseDouble(formatter.format(row.get(2))));
                row.set(3, Double.parseDouble(formatter.format(row.get(3))));
                row.set(4, Double.parseDouble(formatter.format(row.get(4))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return data;
    }
}
