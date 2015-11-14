package co.infinum.productive.helpers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.lang.reflect.Type;

/**
 * Created by mjurinic on 11.11.15..
 */
public class DateTimeSerializer implements JsonSerializer<DateTime>, JsonDeserializer<DateTime> {

    public static final DateTimeFormatter DATE_FORMAT = ISODateTimeFormat.dateTime();

    public DateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String stringDate = json.getAsString();

        if (stringDate.length() != 0) {
            return DATE_FORMAT.parseDateTime(stringDate);
        }

        return null;
    }

    @Override
    public JsonElement serialize(DateTime src, Type typeOfSrc, JsonSerializationContext context) {
        String ret = "";

        if (src != null) {
            ret = src.toString(DATE_FORMAT);
        }

        return context.serialize(ret);
    }
}
