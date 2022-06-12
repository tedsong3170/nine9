package kr.co.janet.backend.recruit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.janet.backend.recruit.converter.Converter;
import kr.co.janet.backend.recruit.converter.JsonConverter;
import kr.co.janet.backend.recruit.parser.CSVParser;
import kr.co.janet.backend.recruit.parser.JSONParser;
import kr.co.janet.backend.recruit.parser.Parser;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
public class RecruitApp
{
    Converter<JSONArray> converter = JsonConverter.getInstance();
    Parser csvParser = CSVParser.getInstance();
    Parser jsonParser = JSONParser.getInstance();
    String csvFileData = "";

    public void run()
    {
        readCSVFileAndPrint();
        convertToJsonAndSave();
        readJsonFileAndPrintSpecificParam();
    }

    protected void readCSVFileAndPrint()
    {
        try
        {
            Path path = Paths.get("./TOP20.csv");
            csvFileData = Files.readString(path, StandardCharsets.UTF_8);

            System.out.println(csvFileData);
        }
        catch ( IOException e )
        {
            log.error("File Read Error. Path : {}", e.getMessage() );
        }
    }

    protected void convertToJsonAndSave()
    {
        JSONArray data = converter.convert(csvParser.parsing(csvFileData));

        try
        {
            ObjectMapper mapper = new ObjectMapper();
            String prettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(data);

            Path path = Paths.get("./TOP20.json");
            Files.write(
                    path,
                    prettyJson.getBytes(StandardCharsets.UTF_8)
            );
        }
        catch ( JsonProcessingException e )
        {
            log.error("JSON Read Error");
        }
        catch ( IOException e )
        {
            log.error("File Write Error. Path : {}", e.getMessage() );
        }
    }

    protected void readJsonFileAndPrintSpecificParam()
    {
        try
        {
            Path path = Paths.get("./TOP20.json");
            String jsonFileData = Files.readString(path, StandardCharsets.UTF_8);

            ArrayList<HashMap<String, String>> parsedData = jsonParser.parsing(jsonFileData);

            for ( HashMap<String, String> row : parsedData )
            {
                System.out.println(row.get("licenseOrgan"));
            }
        }
        catch ( IOException e )
        {
            log.error("File Read Error. Path : {}", e.getMessage() );
        }
    }

    public static void main(String[] args)
    {
        RecruitApp recruitApp = new RecruitApp();
        recruitApp.run();
    }
}
