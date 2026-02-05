package com.example.httpPractice.catExample;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

public class Cat {
    public static final String CAT_URL = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";

    public static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        try (CloseableHttpClient httpClient = CloseableHttpClient()) {
            HttpGet request = new HttpGet(CAT_URL);
            request.addHeader("Accept", "application/json");

            try (CloseableHttpResponse response = httpClient.execute(request)) {
                System.out.println("Анализ HTTP-ответа: ");
                System.out.println("Стартовая строка: " + response.getStatusLine());
                System.out.println("    Заголовки: ");
                for (var header : response.getAllHeaders()) {
                    System.out.println("    " + header.getName() + " : " + header.getValue());
                }

                String jsonResponse = EntityUtils.toString(response.getEntity());
                System.out.println("Message body: " + jsonResponse);

                System.out.println();

                List<PostCat> postsCat = mapper.readValue(jsonResponse, new TypeReference<List<PostCat>>() {});
                postsCat.stream().filter(post -> post.getUpvotes() != null && post.getUpvotes() > 0)
                        //.limit(2)
                        .forEach(System.out::println);

                // Проверка на наличие оставшихся элементов
                /*
                if (postsCat.stream().filter(p -> p.getUpvotes() != null && p.getUpvotes() > 0).count() > 2) {
                    System.out.println("...");
                }
                */

            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static CloseableHttpClient CloseableHttpClient() {
        return HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build()).
                build();
    }
}
