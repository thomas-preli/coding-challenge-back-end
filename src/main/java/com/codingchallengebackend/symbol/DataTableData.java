package com.codingchallengebackend.symbol;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
class DataTableData {

    private List<Map<String, String>> columns;

    private List<List<Object>> data;
}
