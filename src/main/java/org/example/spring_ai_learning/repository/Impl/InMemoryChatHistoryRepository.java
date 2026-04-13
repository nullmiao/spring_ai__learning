package org.example.spring_ai_learning.repository.Impl;

import org.example.spring_ai_learning.repository.ChatHistoryRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class InMemoryChatHistoryRepository implements ChatHistoryRepository {

private final Map<String,List<String>> chatHistoryMap=new HashMap<>();

     @Override
    public void save(String type, String chatId) {
        List<String> chatIds=chatHistoryMap.computeIfAbsent(type, k->new ArrayList<>());
        if(chatIds.contains(chatId)){
            return;
        }
        chatIds.add(chatId);
    }

    @Override
    public List<String> getChatIds(String type) {
       return chatHistoryMap.getOrDefault(type,List.of());
    }
}
