package com.codingchallengebackend.symbol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    String getHistoricalSymbolData(String symbol) {
        return symbolRepository.getHistoricalSymbolData(symbol);
    }
}
