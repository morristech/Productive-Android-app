package co.infinum.productive.helpers;

/**
 * Created by mjurinic on 20.01.16..
 */
public final class TrimHtml {

    private TrimHtml() {
        // checkstyle
    }

    public static CharSequence trim(CharSequence s) {
        if (s.length() == 0) {  // sometimes the body is empty
            return s;
        }

        while (s.charAt(s.length() - 1) == '\n') {
            s = s.subSequence(0, s.length() - 1);
        }

        return s;
    }
}
