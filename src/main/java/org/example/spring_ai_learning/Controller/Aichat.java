package org.example.spring_ai_learning.Controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class Aichat {
@Autowired
    ChatClient chatClient;
    @GetMapping(value="/chat",produces="text/html; charset=utf-8")
    public Flux<String> chat(String message,String chatId) {
       return chatClient.prompt()
               .advisors(a->a.param("chatId",chatId))
               .user(message)
               .stream()
               .content();

    }


}
