package co.infinum.productive.helpers;

import android.content.res.Resources;

import org.joda.time.DateTime;

import co.infinum.productive.R;

/**
 * Created by mjurinic on 21.11.15..
 */
public class ElapsedTimeFormatter {

    private ElapsedTimeFormatter() {
        // checkstyle
    }

    public static String getElapsedTime(DateTime updatedAt, Resources resources) {
        DateTime currentTime = new DateTime();
        String ret = "";

        int years = Math.abs(currentTime.getYear() - updatedAt.getYear());
        int months = Math.abs(currentTime.getMonthOfYear() - updatedAt.getMonthOfYear());
        int days = Math.abs(currentTime.getDayOfMonth() - updatedAt.getDayOfMonth());
        int hours = Math.abs(currentTime.getHourOfDay() - updatedAt.getHourOfDay());
        int minutes = Math.abs(currentTime.getMinuteOfHour() - updatedAt.getMinuteOfHour());
        int seconds = Math.abs(currentTime.getSecondOfMinute() - updatedAt.getSecondOfMinute());

        if (years != 0) {
            ret += years + resources.getString(R.string.year_text);
        } else if (months != 0) {
            ret += months + resources.getString(R.string.month_text);
        } else if (days != 0) {
            ret += days + resources.getString(R.string.day_text);
        } else if (hours != 0) {
            ret += hours + resources.getString(R.string.hour_text);
        } else if (minutes != 0) {
            ret += minutes + resources.getString(R.string.minute_text);
        } else if (seconds != 0) {
            ret += seconds + resources.getString(R.string.second_text);
        }

        return ret;
    }
}
