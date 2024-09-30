package org.example;

import java.io.*;
import java.lang.reflect.Field;
import java.util.List;

public class CSVWriter {

    private final String csvSeparator;

    public CSVWriter(String csvSeparator) {
        this.csvSeparator = csvSeparator;
    }

    public CSVWriter() {
        this.csvSeparator = ",";
    }

    public <T> void writeToFile(List<T> list, String filePath) throws IOException, IllegalAccessException {

        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("The list of objects is empty");
        }

        final Class<?> clazz = list.getFirst().getClass();
        final Field[] fields = clazz.getDeclaredFields();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // create csv header
            for (int i = 0; i < fields.length; i++) {
                writer.write(fields[i].getName());
                if (i == fields.length - 1) {
                    writer.newLine();
                    break;
                }
                writer.write(csvSeparator);
            }

            // fill columns
            for (T item : list) {
                for (int i = 0; i < fields.length; i++) {
                    fields[i].setAccessible(true);
                    writer.write(fields[i].get(item).toString());
                    if (i == fields.length - 1) {
                        writer.newLine();
                        break;
                    }
                    writer.write(csvSeparator);
                }
            }
        }
    }
}
