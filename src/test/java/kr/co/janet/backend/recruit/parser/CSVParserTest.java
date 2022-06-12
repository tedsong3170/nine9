package kr.co.janet.backend.recruit.parser;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class CSVParserTest
{
    @Test
    public void CSVParsingTest() throws IOException
    {
        Parser csvParser = CSVParser.getInstance();

        Path path = Paths.get("src", "test", "resources", "csv_test.csv");
        String fileData = Files.readString(path, StandardCharsets.UTF_8);

        ArrayList<HashMap<String, String>> parsedData = csvParser.parsing(fileData);
        Assertions.assertThat(parsedData.size()).isEqualTo(8);
    }
}
