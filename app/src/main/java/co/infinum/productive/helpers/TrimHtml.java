package co.infinum.productive.helpers;

/**
 * Created by mjurinic on 20.01.16..
 */
public final class TrimHtml {

    public static CharSequence Trim(CharSequence s) {
        if (s.length() == 0) return s;  // sometimes the body is empty

        while (s.charAt(s.length() - 1) == '\n') {
            s = s.subSequence(0, s.length() - 1);
        }

        return s;
    }
}
