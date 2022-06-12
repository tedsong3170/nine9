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

public class JSONParserTest
{
    @Test
    public void JSONParsingTest() throws IOException
    {
        Parser jsonParser = JSONParser.getInstance();

        Path path = Paths.get("src", "test", "resources", "json_test.json");
        String fileData = Files.readString(path, StandardCharsets.UTF_8);

        ArrayList<HashMap<String, String>> parsedData = jsonParser.parsing(fileData);
        Assertions.assertThat(parsedData.size()).isEqualTo(8);
    }
}
