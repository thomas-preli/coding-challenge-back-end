package com.codingchallengebackend;

import com.codingchallengebackend.symbol.SymbolRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

@SpringBootApplication
public class CodingChallengeBackEndApplication {

    public static void main(String[] args) throws IOException {
        loadSymbols();

        SpringApplication.run(CodingChallengeBackEndApplication.class, args);
    }

    private static void loadSymbols() throws IOException {
        Map<String, String> symbols = new HashMap<>();

        Collection<String> fileNames = new HashSet<>(Arrays.asList("amex.csv", "nasdaq.csv", "nyse.csv"));

        for (String fileName : fileNames) {
            Reader in = new FileReader("src" + File.separator + "main" + File.separator + "resources" +
                    File.separator + "static" + File.separator + fileName);
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);

            for (CSVRecord record : records) {
                symbols.put(record.get("Symbol"), record.get("Name"));
            }
        }

        SymbolRepository.saveSymbols(symbols);
    }
}
