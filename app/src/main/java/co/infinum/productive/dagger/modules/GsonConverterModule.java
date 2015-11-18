package co.infinum.productive.dagger.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import javax.inject.Singleton;

import co.infinum.productive.helpers.DateTimeSerializer;
import co.infinum.productive.helpers.LocalDateSerializer;
import dagger.Module;
import dagger.Provides;
import retrofit.Converter;
import retrofit.GsonConverterFactory;

@Module
public class GsonConverterModule {

    @Provides
    @Singleton
    public Converter.Factory provideConverterFactory() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(DateTime.class, new DateTimeSerializer())
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                .create();

        return GsonConverterFactory.create(gson);
    }
}