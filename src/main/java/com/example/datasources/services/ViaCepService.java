package com.example.datasources.services;

import com.example.entities.AddressEntite;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ViaCepService {
    private final WebClient webClient;

    private final String url = "http://viacep.com.br/ws/%s/json/";

    public ViaCepService(WebClient webClient) {
        this.webClient = webClient;
    }

    public AddressEntite getAddressByCep(String cep){
        String urlFormatada = String.format(url,cep);
        AddressEntite address = webClient.get()
                                    .uri(String.format(url,cep))
                                    .retrieve()
                                    .bodyToMono(AddressEntite.class)
                                    .block();
        return address;
    }
}
