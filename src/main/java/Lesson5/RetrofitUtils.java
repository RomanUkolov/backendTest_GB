package Lesson5;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



@UtilityClass
public class RetrofitUtils {

    Properties prop = new Properties();
    private static InputStream configFile;

    static {
        try {
            configFile = new FileInputStream("src/main/resources/var.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getBaseUrl() throws IOException {
        prop.load(configFile);
        return prop.getProperty("url");
    }

    public Retrofit getRetrofit() throws IOException {
        return new Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }
}