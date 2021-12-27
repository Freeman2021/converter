package ru.smartsoft.converter.configuration;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public Gson getGson() {
        return new Gson();
    }

    public Pair getPair(String charCode, String name) {
        return new Pair(charCode, name);
    }

    public static class Pair{
        String charCode;
        String name;

        public Pair(String charCode, String name) {
            this.charCode = charCode;
            this.name = name;
        }
    }
}
