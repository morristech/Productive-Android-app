package co.infinum.productive.helpers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.lang.reflect.Type;

/**
 * Created by mjurinic on 16.11.15..
 */
public class LocalDateSerializer implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    public static final DateTimeFormatter DATE_FORMAT = ISODateTimeFormat.date();

    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String stringDate = json.getAsString();

        if (stringDate.length() != 0) {
            return DATE_FORMAT.parseLocalDate(stringDate);
        }

        return null;
    }

    @Override
    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
        String ret = "";

        if (src != null) {
            ret = src.toString(DATE_FORMAT);
        }

        return context.serialize(ret);
    }
}
