package com.practice.newsreader.model.json;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.practice.newsreader.model.Media;
import com.practice.newsreader.model.MediaList;
import com.practice.newsreader.model.Result;

import java.lang.reflect.Type;

/**
 * Created by h125673 on 2/17/16.
 */
public class MediaListJsonConverter implements JsonDeserializer<MediaList>
{

    @Override
    public MediaList deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        if(json != null && json.isJsonArray())
        {
            Gson gson = new Gson();
            MediaList result = gson.fromJson(json, MediaList.class);
            return result;
        }
        return null;
    }
}
