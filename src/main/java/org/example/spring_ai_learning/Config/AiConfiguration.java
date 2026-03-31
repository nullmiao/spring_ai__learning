package org.example.spring_ai_learning.Config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfiguration {
    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    @Value("${spring.ai.openai.base-url}")
    private String baseUrl;

    /**
     * 配置 DeepSeek 模型
     */
    @Bean
    public OpenAiChatModel deepSeekChatModel() {
        // 1. 构建 DeepSeek API 客户端
        OpenAiApi openAiApi = new OpenAiApi(baseUrl, apiKey);

        // 2. 配置对话参数
        OpenAiChatOptions options = OpenAiChatOptions.builder()
                .model("deepseek-chat")
                .temperature(0.7)
                .maxTokens(2048)
                .build();

        // 3. 返回 ChatModel
        return new OpenAiChatModel(openAiApi, options);
    }

    /**
     * 注入 ChatClient（最方便调用）
     */
    @Bean
    public ChatClient deepSeekChatClient(OpenAiChatModel chatModel) {
        return ChatClient.builder(chatModel).
         defaultSystem("你是一个有用的助手，帮助用户解答问题和提供信息。")
        .build();
    }
}
