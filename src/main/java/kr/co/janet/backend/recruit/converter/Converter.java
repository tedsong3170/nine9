package kr.co.janet.backend.recruit.converter;

import java.util.ArrayList;
import java.util.HashMap;

public interface Converter<T>
{
    T convert(ArrayList<HashMap<String, String>> data);
}
