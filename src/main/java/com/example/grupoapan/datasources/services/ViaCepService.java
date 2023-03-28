package com.example.grupoapan.datasources.services;

import com.example.grupoapan.entities.Address;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ViaCepService {
    private final WebClient webClient;

    private final String url = "https://viacep.com.br/ws/%s/json/";

    public ViaCepService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Address getAddressByCep(String cep){
        Address  address = webClient.get()
                                    .uri(String.format(url,cep))
                                    .retrieve()
                                    .bodyToMono(Address.class)
                                    .block();
        return address;
    }
}
