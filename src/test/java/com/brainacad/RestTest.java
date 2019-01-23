package com.brainacad;

import org.apache.http.HttpResponse;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RestTest{

    private static final String URL="https://reqres.in/";

    @Test//GET метод
    public void checkGetResponseStatusCode() throws IOException {
        String endpoint="/api/users";

        //TODO: Избавится он хедеров в тесте добавив методы с хедерами по умолчанию в класс HttpClientHelper
        //Создаём переменую headers типа Map
       // Map<String, String> headers=new HashMap<>();
        //Добавляем в headers наш заголовок
      // headers.put("User-Agent", "My-Test-User-Agent");
        //Выполняем REST GET запрос с нашими параметрами
        // и сохраняем результат в переменную response.
       HttpResponse response = HttpClientHelper.get(URL+endpoint,"page=2");

        //получаем статус код из ответа
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + statusCode);
        Assert.assertEquals("Response status code should be 200", 200, statusCode);
    }

    @Test//GET метод
    public void checkGetResponseBodyNotNull() throws IOException {
        String endpoint="/api/users";

        //TODO: Избавится он хедеров в тесте добавив методы с хедерами по умолчанию в класс HttpClientHelper
        //Создаём переменую headers типа Map
        Map<String, String> headers=new HashMap<>();
        //Добавляем в headers наш заголовок
        headers.put("User-Agent", "My-Test-User-Agent");

        //Выполняем REST GET запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.get(URL+endpoint,"page=2", headers);

        //Конвертируем входящий поток тела ответа в строку
        String body=HttpClientHelper.getBodyFromResponse(response);
        System.out.println(body);
        Assert.assertNotEquals("Body shouldn't be null", null, body);
    }

    @Test//POST метод
    public void checkPostResponseStatusCode() throws IOException {
        String endpoint="/api/users";

        //TODO: Избавится он хедеров в тесте добавив методы с хедерами по умолчанию в класс HttpClientHelper
        //Создаём переменую headers типа Map
        Map<String, String> headers=new HashMap<>();
        //Добавляем в headers наш заголовок
        headers.put("User-Agent", "My-Test-User-Agent");

        //создаём тело запроса
        String requestBody="{\"name\": \"morpheus\",\"job\": \"leader\"}";

        //Выполняем REST POST запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.post(URL+endpoint,requestBody, headers);

        //получаем статус код из ответа
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + statusCode);
        Assert.assertEquals("Response status code should be 201", 201, statusCode);
    }

    @Test//POST метод
    public void checkPostResponseBodyNotNull() throws IOException {
        String endpoint="/api/users";

        //TODO: Избавится он хедеров в тесте добавив методы с хедерами по умолчанию в класс HttpClientHelper
        //Создаём переменую headers типа Map
        Map<String, String> headers=new HashMap<>();
        //Добавляем в headers наш заголовок
        headers.put("User-Agent", "My-Test-User-Agent");

        //создаём тело запроса
        String requestBody="{\"name\": \"morpheus\",\"job\": \"leader\"}";

        //Выполняем REST POST запрос с нашими параметрами
        // и сохраняем результат в переменную response.
        HttpResponse response = HttpClientHelper.post(URL+endpoint,requestBody, headers);

        //Конвертируем входящий поток тела ответа в строку
        String body=HttpClientHelper.getBodyFromResponse(response);
        System.out.println(body);
        Assert.assertNotEquals("Body shouldn't be null", null, body);
    }
    @Test//GET метод
    public void singleUser() throws IOException {
        String endpoint="/api/users";

                HttpResponse response = HttpClientHelper.get(URL+endpoint,"2");

        //получаем статус код из ответа
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + statusCode);
        Assert.assertEquals("Response status code should be 200", 200, statusCode);
    }

    @Test//GET метод
    public void singleUserNotFound() throws IOException {
        String endpoint="/api/users/23";

        HttpResponse response = HttpClientHelper.get(URL+endpoint,null);

        //получаем статус код из ответа
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + statusCode);
        Assert.assertEquals("Response status code should be 200", 404, statusCode);
    }
    //TODO: напишите по тесткейсу на каждый вариант запроса на сайте https://reqres.in
    //TODO: в тескейсах проверьте Result Code и несколько параметров из JSON ответа (если он есть)

    @Test//GET метод
    public void listResurse() throws IOException {
        String endpoint="/api/unknown";

        HttpResponse response = HttpClientHelper.get(URL+endpoint,"null");

        //получаем статус код из ответа
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + statusCode);
        Assert.assertEquals("Response status code should be 200", 200, statusCode);
        String body = HttpClientHelper.getBodyFromResponse(response);
        List dataFromXpath = JsonUtils.listFromJSONByPath(body, "$.data[*].color");
        System.out.println("Json.All.Colors :" + dataFromXpath);
    }

    @Test//GET метод
    public void singleResurse() throws IOException {
        String endpoint="/api/unknown/2";

        HttpResponse response = HttpClientHelper.get(URL+endpoint,"null");

        //получаем статус код из ответа
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + statusCode);
        Assert.assertEquals("Response status code should be 200", 200, statusCode);
        String body = HttpClientHelper.getBodyFromResponse(response);
        String dataFromXpath = JsonUtils.stringFromJSONByPath(body, "$.data.name");
        if (
            dataFromXpath.equals("fuchsia rose"))
                    System.out.println("The name is correct!");
            else System.out.println("The name is false!");
              }
    }
