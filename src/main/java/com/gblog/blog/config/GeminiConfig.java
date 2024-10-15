package com.gblog.blog.config;
import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class GeminiConfig {

    private static final String MODEL_NAME = "gemini-pro";
    private static final String LOCATION = "asia-southeast1";
    private static final String PROJECT_ID = "";

    @Bean
    public VertexAI vertexAI() {
        return new VertexAI(PROJECT_ID, LOCATION);
    }

    @Bean
    public GenerativeModel getModel(VertexAI vertexAI) {
        return new GenerativeModel(MODEL_NAME, vertexAI);
    }
}