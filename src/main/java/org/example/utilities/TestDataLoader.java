package org.example.utilities;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.util.List;

public class TestDataLoader {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String TEST_DATA_PATH = ConfigManager.getProperty("test.data.path");

    @SneakyThrows
    public static <T> List<T> loadTestData(String fileName, Class<T> valueType){
        File file = new File(TEST_DATA_PATH + fileName);
        return objectMapper.readValue(file, new TypeReference<List<T>>() {});
    }
}
