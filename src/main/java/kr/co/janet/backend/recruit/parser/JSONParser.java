package kr.co.janet.backend.recruit.parser;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
public class JSONParser implements Parser
{
    private static class JSONParserClass
    {
        private static final JSONParser instance = new JSONParser();
    }

    public static JSONParser getInstance()
    {
        return JSONParserClass.instance;
    }

    @Override
    public ArrayList<HashMap<String, String>> parsing(String input)
    {
        ArrayList<HashMap<String, String>> parsedData = new ArrayList<>();

        org.json.simple.parser.JSONParser jsonParser = new org.json.simple.parser.JSONParser();
        try
        {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(input);

            for ( Object json : jsonArray )
            {
                HashMap<String, String> row = new HashMap<>();

                for ( Object key : ((JSONObject) json).keySet() )
                {
                    Object val = ((JSONObject) json).get(key);
                    row.put((String) key, (String) val);
                }
                parsedData.add(row);
            }
        }
        catch (ParseException | ClassCastException e )
        {
            log.error("JSON Format Error {}", input);
            throw new IllegalStateException();
        }
        return parsedData;
    }
}
