package kr.co.janet.backend.recruit.parser;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Slf4j
public class CSVParser implements Parser
{
    private static class CSVParserClass
    {
        private static final CSVParser instance = new CSVParser();
    }

    public static CSVParser getInstance()
    {
        return CSVParser.CSVParserClass.instance;
    }

    @Override
    public ArrayList<HashMap<String, String>> parsing(String input)
    {
        ArrayList<String> keys = new ArrayList<>();
        ArrayList<HashMap<String, String>> parsedData = new ArrayList<>();

        List<String> lines = Arrays.asList(input.split("\n"));

        for (String line : lines)
        {
            List<String> fields = Arrays.asList(line.split(","));

            if (keys.isEmpty() )
            {
                keys = new ArrayList<>(fields);
            }
            else
            {
                if (fields.size() != keys.size())
                {
                    log.error("CSV Format Error {}", input);
                    throw new IllegalStateException();
                }

                HashMap<String, String> row = new HashMap<>();

                for ( int i = 0; i < keys.size(); i++ )
                {
                    row.put(keys.get(i), fields.get(i));
                }

                parsedData.add(row);
            }
        }
        return parsedData;
    }
}
