package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;


public class GsonUtil {

    public static <T> T parsePojo(String jsonString, Class<T> cls) {
        T t = null;
        try {
            t = new Gson().fromJson(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public static <T> List<T> parsePojoList(String jsonString) {
        List<T> tList = null;
        try {
            tList = new Gson().fromJson(jsonString, new TypeToken<List<T>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tList;
    }

    public static String writeValueAsString(Object o) {
        String jsonString = new Gson().toJson(o);
        return jsonString;
    }
}