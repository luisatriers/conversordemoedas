package br.com.conversordemoedas.principal;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConexaoAPI {
    String chaveAPI = "9e0eae0a8f6164fc225ef1a3";
    String currency = "USD";
    String endereco;

    private void atualizaEndereco() {
        this.endereco = "https://v6.exchangerate-api.com/v6/" + chaveAPI + "/latest/" + currency;
    }

    public String conectaAPI() {
        atualizaEndereco();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        HttpResponse<String> response;

        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String dadosAPI = response.body();
        return dadosAPI;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
