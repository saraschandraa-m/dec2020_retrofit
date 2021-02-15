package model;

import org.json.JSONObject;

public class Source {

    public String id;
    public String name;

    public static Source parseSource(JSONObject jsonObject){
        Source source = new Source();

        source.id = jsonObject.optString("id");
        source.name = jsonObject.optString("name");

        return source;
    }

}
