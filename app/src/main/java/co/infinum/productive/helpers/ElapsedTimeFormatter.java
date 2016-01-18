package co.infinum.productive.helpers;

import android.content.res.Resources;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import co.infinum.productive.R;

/**
 * Created by mjurinic on 21.11.15..
 */
public class ElapsedTimeFormatter {

    private ElapsedTimeFormatter() {
        // checkstyle
    }

    public static String getElapsedTime(DateTime updatedAt, Resources resources) {
        PeriodFormatter formatter = new PeriodFormatterBuilder()
                .appendSeconds().appendSuffix(resources.getString(R.string.second_text) + '\n')
                .appendMinutes().appendSuffix(resources.getString(R.string.minute_text) + '\n')
                .appendHours().appendSuffix(resources.getString(R.string.hour_text) + '\n')
                .appendDays().appendSuffix(resources.getString(R.string.day_text) + '\n')
                .appendMonths().appendSuffix(resources.getString(R.string.month_text) + '\n')
                .appendYears().appendSuffix(resources.getString(R.string.year_text) + '\n')
                .printZeroNever()
                .toFormatter();

        String[] elapsed = formatter.print(new Period(updatedAt, new DateTime())).split("\n");

        return elapsed[elapsed.length - 1];
    }
}
