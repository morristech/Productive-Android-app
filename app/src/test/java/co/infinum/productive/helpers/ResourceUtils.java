package co.infinum.productive.helpers;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by noxqs on 10.11.15..
 */
public class ResourceUtils {

    private static final String MOCK_DATA_DIRECTORY = "mockdata/%s";

    public ResourceUtils() {
    }

    public static String convertStreamToString(InputStream is) {
        Scanner s = new Scanner(is, "UTF-8").useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public static String readFromFile(String filename) {
        InputStream is = ResourceUtils.class.getClassLoader().getResourceAsStream(String.format(MOCK_DATA_DIRECTORY, filename));
        return convertStreamToString(is);
    }
}
