package com.karthick.gemini;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
@AllArgsConstructor
public class QnAService {

    @Value("${gemini.api.url}")
    private String geminiApiUrl;

    @Value("${gemini.api.key}")
    private  String geminiApikey;
    private WebClient webClient;
    @Autowired
    public QnAService(WebClient.Builder webclient)
    {
        this.webClient=webclient.build();
    }

    public String getAnswer(String question) {
        //construct the request payload
        Map<String,Object> requestBody=Map.of("contents",new Object[]{
        Map.of("parts",new Object[]{
                Map.of("text",question)
        })
});

//------------------------------structure of the payload :

//{
//    "contents": [{
//        "parts":[{"text": "Explain how AI works"}]
//    }]
//}



        return webClient.post()
                .uri(geminiApiUrl + geminiApikey)
                .header("Content-Type","application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class).block();

                //make the api call
                //return response

    }
}
