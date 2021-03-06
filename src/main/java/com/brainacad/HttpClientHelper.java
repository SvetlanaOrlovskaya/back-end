package com.brainacad;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static org.apache.http.protocol.HTTP.USER_AGENT;

public class HttpClientHelper {
    //   public static void addHeaderToMap (Map<String, String> headers, String key, String value){
    //       headers.put(key, value);
    // }


    public static HttpResponse get(String endpointUrl, String parameters) throws IOException {
        //TODO: написать метод для GET запроса с хедерами по умолчанию
        //Создаём переменую headers типа Map
        Map<String, String> headers = new HashMap<>();
        //Добавляем в headers наш заголовок
        headers.put("User-Agent", "My-Test-User-Agent");

        HttpResponse forReturn = get(endpointUrl, parameters, headers);
        return forReturn;
    }

    //REST GET запрос
    public static HttpResponse get(String endpointUrl, String parameters, Map<String, String> headers) throws IOException {
        //Создаём экземпляр HTTP клиента
        HttpClient client = HttpClientBuilder.create().build();
        //Создаём HTTP GET запрос из URL и параметров
        HttpGet request = new HttpGet(endpointUrl + "?" + parameters);

        //добавляем в запрос необходимые хедеры
        for (String headerKey : headers.keySet()) {
            request.addHeader(headerKey, headers.get(headerKey));
        }

        //выполняем запрос в HTTP клиенте и получаем ответ
        HttpResponse response = client.execute(request);

        //возвращаем response
        return response;
    }


    public static HttpResponse post(String endpointUrl, String parameters) throws IOException {
        //TODO: написать метод для POST запроса с хедерами по умолчанию
        //Создаём переменую headers типа Map
        Map<String, String> headers = new HashMap<>();
        //Добавляем в headers наш заголовок
        headers.put("User-Agent", "My-Test-User-Agent");
        HttpResponse forReturn = post(endpointUrl, parameters, headers);
        return forReturn;
    }

    public static HttpResponse post(String endpointUrl, String body, Map<String, String> headers) throws IOException {
        //Создаём экземпляр HTTP клиента
        HttpClient client = HttpClientBuilder.create().build();
        //Создаём HTTP POST запрос из URL и параметров
        HttpPost post = new HttpPost(endpointUrl);

        //добавляем в запрос необходимые хедеры
        for (String headerKey : headers.keySet()) {
            post.addHeader(headerKey, headers.get(headerKey));
        }

        //добавляем к запросу тело запроса
        post.setEntity(new StringEntity(body));

        //выполняем запрос в HTTP клиенте и получаем ответ
        HttpResponse response = client.execute(post);

        //возвращаем response
        return response;
    }

    //TODO: допишите методы для запросов PUT, PATCH и DELETE

    // MY DELETE
    public static HttpResponse delete(String endpointUrl) throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", "My-Test-User-Agent");

        HttpResponse forReturn = delete(endpointUrl, headers);
        return forReturn;
    }

    //REST  запрос
    public static HttpResponse delete(String endpointUrl, Map<String, String> headers) throws IOException {
        //Создаём экземпляр HTTP клиента
        HttpClient client = HttpClientBuilder.create().build();
        //Создаём HTTP GET запрос из URL и параметров
        HttpDelete request = new HttpDelete(endpointUrl);

        //добавляем в запрос необходимые хедеры
        for (String headerKey : headers.keySet()) {
            request.addHeader(headerKey, headers.get(headerKey));
        }

        //выполняем запрос в HTTP клиенте и получаем ответ
        HttpResponse response = client.execute(request);

        //возвращаем response
        return response;
    }
// MY PUT

    public static HttpResponse put(String endpointUrl, String parameters) throws IOException {
        Map<String, String> headers = new HashMap<>();
        headers.put("User-Agent", "My-Test-User-Agent");
        HttpResponse forReturn = post(endpointUrl, parameters, headers);
        return forReturn;
    }

    public static HttpResponse put(String endpointUrl, String body, Map<String, String> headers) throws IOException {
        //Создаём экземпляр HTTP клиента
        HttpClient client = HttpClientBuilder.create().build();
        //Создаём HTTP POST запрос из URL и параметров
        HttpPut put = new HttpPut(endpointUrl);

        //добавляем в запрос необходимые хедеры
        for (String headerKey : headers.keySet()) {
            put.addHeader(headerKey, headers.get(headerKey));
        }

        //добавляем к запросу тело запроса
        put.setEntity(new StringEntity(body));

        //выполняем запрос в HTTP клиенте и получаем ответ
        HttpResponse response = client.execute(put);

        //возвращаем response
        return response;
    }


    // MY PATCH

    public static HttpResponse patch(String endpointUrl, String parameters) throws IOException {
        //TODO: написать метод для POST запроса с хедерами по умолчанию
        //Создаём переменую headers типа Map
        Map<String, String> headers = new HashMap<>();
        //Добавляем в headers наш заголовок
        headers.put("User-Agent", "My-Test-User-Agent");
        HttpResponse forReturn = patch(endpointUrl, parameters, headers);
        return forReturn;
    }

    public static HttpResponse patch(String endpointUrl, String body, Map<String, String> headers) throws IOException {
        //Создаём экземпляр HTTP клиента
        HttpClient client = HttpClientBuilder.create().build();
        //Создаём HTTP POST запрос из URL и параметров
        HttpPatch patch = new HttpPatch(endpointUrl);

        //добавляем в запрос необходимые хедеры
        for (String headerKey : headers.keySet()) {
            patch.addHeader(headerKey, headers.get(headerKey));
        }

        //добавляем к запросу тело запроса
        patch.setEntity(new StringEntity(body));

        //выполняем запрос в HTTP клиенте и получаем ответ
        HttpResponse response = client.execute(patch);

        //возвращаем response
        return response;
    }


    public static String getBodyFromResponse(HttpResponse response) throws IOException {
        //создаём ридер буффера и передаём в него входящий поток респонса
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer result = new StringBuffer();
        String line;

        //получаем в цикле построчно строки из входящего потока и собираем в одну строку
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();

    }
}
