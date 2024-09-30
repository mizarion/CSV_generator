package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");

        List<BusinessObject> list = List.of(new BusinessObject(1, "name1")
                , new BusinessObject(2, "name2"), new BusinessObject(3, "name3"));

        CSVWriter writer = new CSVWriter();
        writer.writeToFile(list, "file.csv");
    }
}