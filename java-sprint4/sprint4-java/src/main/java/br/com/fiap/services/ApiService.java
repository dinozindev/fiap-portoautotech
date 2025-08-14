package br.com.fiap.services;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ApiService {

    private static final String URL = "http://localhost:5000/predict"; // URL da API

    public JsonObject realizarPredicao(JsonObject dados) {
        JsonObject resposta = null;

        HttpPost post = new HttpPost(URL);
        post.setHeader("Content-Type", "application/json");

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            StringEntity entity = new StringEntity(dados.toString());
            post.setEntity(entity);

            try (CloseableHttpResponse response = httpClient.execute(post)) {
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    String result = EntityUtils.toString(responseEntity);
                    Gson gson = new Gson();
                    resposta = gson.fromJson(result, JsonObject.class);
                }
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resposta;
    }
}
