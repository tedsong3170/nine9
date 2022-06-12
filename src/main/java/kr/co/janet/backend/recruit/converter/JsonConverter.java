package kr.co.janet.backend.recruit.converter;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;


@Slf4j
public class JsonConverter implements Converter<JSONArray>
{
    private static class JsonConverterClass
    {
        private static final JsonConverter instance = new JsonConverter();
    }

    public static JsonConverter getInstance()
    {
        return JsonConverterClass.instance;
    }

    public JSONArray convert(ArrayList<HashMap<String, String>> data)
    {
        JSONArray jsonArray = new JSONArray();

        for ( HashMap<String, String> row : data )
        {
            JSONObject jsonObject = new JSONObject();

            for ( String key : row.keySet() )
            {
                jsonObject.put(key, row.get(key));
            }
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }
}
