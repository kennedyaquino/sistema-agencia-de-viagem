package com.br.jornada.milhas.services.impl;

import com.br.jornada.milhas.services.IArtificialIteligenteService;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class InteligenciaArtificialService implements IArtificialIteligenteService {

    @Value("${api.openia.token}")
    private String token;

    @Override
    public String gerarTextoDescritivoDestino(String texto) {

        OpenAiService service = new OpenAiService(token);

        CompletionRequest completionRequest = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt(texto)
                .maxTokens(500)
                .temperature(0.7)
                .build();

        String result = service.createCompletion(completionRequest).getChoices().get(0).getText();

        return result;
    }
}
