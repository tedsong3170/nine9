package kr.co.janet.backend.recruit.parser;

import java.util.ArrayList;
import java.util.HashMap;

public interface Parser
{
    ArrayList<HashMap<String, String>> parsing(String input);
}
