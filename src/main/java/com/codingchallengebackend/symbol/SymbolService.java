package com.codingchallengebackend.symbol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SymbolService {

    private SymbolRepository symbolRepository;

    @Autowired
    public SymbolService(SymbolRepository symbolRepository) {
        this.symbolRepository = symbolRepository;
    }

    @Autowired
    public Map<String, String> getSymbols() {
        return symbolRepository.getSymbols();
    }
}
