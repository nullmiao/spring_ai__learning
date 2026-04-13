package org.example.spring_ai_learning.Controller;

import org.example.spring_ai_learning.repository.ChatHistoryRepository;
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
@Autowired
private  ChatHistoryRepository chatHistoryRepository;
    @GetMapping(value="/chat",produces="text/html; charset=utf-8")
    public Flux<String> chat(String message,String chatId) {
        //保存用户id
        chatHistoryRepository.save("chat",chatId);
        //请求模型
       return chatClient.prompt()
               .advisors(a->a.param("chatId",chatId))
               .user(message)
               .stream()
               .content();

    }


}
