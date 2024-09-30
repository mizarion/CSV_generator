package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class CSVWriterTest {

    private final CSVWriter csvWriter = new CSVWriter(";");

    @Test
    void writeToFile() throws IOException, IllegalAccessException {
        List<BusinessObject> list = List.of(
                new BusinessObject(1, "name1", List.of("str 1")),
                new BusinessObject(2, "name2", List.of("str 2", "str 3")),
                new BusinessObject(3, "name3", List.of()));

        Path tempFile = Files.createTempFile("temp", null);
        csvWriter.writeToFile(list, tempFile.toAbsolutePath().toString());
        List<String> lines = Files.readAllLines(tempFile);
        Assertions.assertEquals(list.size() + 1, lines.size());
    }


    @Test
    void writeToFileEmptyList() throws IOException {
        List<BusinessObject> list = new ArrayList<>();
        Path tempFile = Files.createTempFile("temp", null);
        Assertions.assertThrows(Exception.class, () -> csvWriter.writeToFile(list, tempFile.toAbsolutePath().toString()));
    }

    @Test
    void writeToIncorrectPath() {
        List<BusinessObject> list = List.of(
                new BusinessObject(1, "name1", List.of("str 1")),
                new BusinessObject(2, "name2", List.of("str 2", "str 3")),
                new BusinessObject(3, "name3", List.of()));
        Assertions.assertThrows(Exception.class, () -> csvWriter.writeToFile(list, null));
    }
}