package kr.co.janet.backend.recruit.converter;

import kr.co.janet.backend.recruit.parser.JSONParser;
import kr.co.janet.backend.recruit.parser.Parser;
import org.assertj.core.api.Assertions;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class JSONConverterTest
{
    @Test
    public void JSONConvertingTest() throws IOException
    {
        Parser jsonParser = JSONParser.getInstance();
        Converter<JSONArray> converter = JsonConverter.getInstance();

        Path path = Paths.get("src", "test", "resources", "json_test.json");
        String fileData = Files.readString(path, StandardCharsets.UTF_8);

        JSONArray data = converter.convert(jsonParser.parsing(fileData));
        Assertions.assertThat(data.size()).isEqualTo(8);

    }
}
