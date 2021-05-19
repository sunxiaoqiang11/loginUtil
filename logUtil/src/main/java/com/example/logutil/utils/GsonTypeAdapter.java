package com.example.logutil.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GsonTypeAdapter extends TypeAdapter {
    @Override
    public void write(JsonWriter jsonWriter, Object o) throws IOException {

    }

    @Override
    public Object read(JsonReader in) throws IOException {
        JsonToken token = in.peek();
        switch (token) {
            case BEGIN_ARRAY:
                List<Object> list = new ArrayList();
                in.beginArray();

                while (in.hasNext()) {
                    list.add(this.read(in));
                }

                in.endArray();
                return list;
            case BEGIN_OBJECT:
                Map<String, Object> map = new LinkedTreeMap();
                in.beginObject();

                while (in.hasNext()) {
                    map.put(in.nextName(), this.read(in));
                }

                in.endObject();
                return map;
            case STRING:
                return in.nextString();
            case NUMBER:
                double v = in.nextDouble();
                long l = (long) v;
                if (v == l) return l;
                else return v;
            case BOOLEAN:
                return in.nextBoolean();
            case NULL:
                in.nextNull();
                return null;
            default:
                throw new IllegalStateException();
        }
    }
}
