package com.codingchallengebackend;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class CodingChallengeBackEndApplication {

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        loadSymbols();

        SpringApplication.run(CodingChallengeBackEndApplication.class, args);
    }

    private static void loadSymbols() throws IOException, SQLException, ClassNotFoundException {
        H2DatabaseService.createWithPreparedStatement();

        Collection<String> fileNames = new HashSet<>(Arrays.asList("amex.csv", "nasdaq.csv", "nyse.csv"));

        Set<String> symbolsInserted = new HashSet<>();

        for (String fileName : fileNames) {
            Reader in = new FileReader("src" + File.separator + "main" + File.separator + "resources" +
                    File.separator + "static" + File.separator + fileName);
            Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);

            for (CSVRecord record : records) {
                String symbol = record.get("Symbol").trim();
                String name = record.get("Name").trim();

                if (!symbolsInserted.contains(symbol)) {
                    H2DatabaseService.insertWithPreparedStatement(symbol, name);
                    symbolsInserted.add(symbol);
                }
            }
        }
    }
}
