package org.example.spring_ai_learning.repository;

import java.util.List;

public interface ChatHistoryRepository {

    void save(String type,String chatId);

    List<String> getChatIds(String type);
}
