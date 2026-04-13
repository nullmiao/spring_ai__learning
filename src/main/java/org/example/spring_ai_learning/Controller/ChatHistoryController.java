package org.example.spring_ai_learning.Controller;

import lombok.RequiredArgsConstructor;
import org.example.spring_ai_learning.entity.vo.MessageVO;
import org.example.spring_ai_learning.repository.ChatHistoryRepository;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ai/history")
@RequiredArgsConstructor
public class ChatHistoryController {
    private final ChatHistoryRepository chatHistoryRepository;
private final ChatMemory chatMemory;

    @GetMapping("/{type}")
    public Object getChatIds(@PathVariable("type") String type) {
        return chatHistoryRepository.getChatIds(type);
    }
    @GetMapping("/{type}/{chatId}")
    public Object getChat(@PathVariable("type") String type, @PathVariable("chatId") String chatId) {
        List<Message> message=chatMemory.get(chatId,Integer.MAX_VALUE);
        if(message==null){
            return List.of();
        }
        return message.stream().map(MessageVO::new).toList();
    }
}
