package com.karthick.gemini;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/qna")
//@RequiredArgsConstructor
public class AIController {

    private final QnAService qnAService ;
    @Autowired
    public AIController(QnAService qnAService)
    {
        this.qnAService=qnAService;
    }
    @PostMapping("/ask")
    public String askQuestion (@RequestBody Map<String ,String> payload)
    {

        String question =payload.get("question");
        String answer = qnAService.getAnswer(question);
//                .map(answer->ResponseEntity.ok(answer))
//                .defaultIfEmpty(ResponseEntity.badRequest().body("No response from AI"));
        return answer!=null? String.valueOf(ResponseEntity.ok(answer)) : String.valueOf(ResponseEntity.status(400).body("null"));
    }
}
