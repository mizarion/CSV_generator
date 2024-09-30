package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<BusinessObject> list = List.of(
                new BusinessObject(1, "name1", List.of("str 1")),
                new BusinessObject(2, "name2", List.of("str 2", "str 3")),
                new BusinessObject(3, "name3", List.of()));

        CSVWriter writer = new CSVWriter();
        writer.writeToFile(list, "file.csv");
    }
}