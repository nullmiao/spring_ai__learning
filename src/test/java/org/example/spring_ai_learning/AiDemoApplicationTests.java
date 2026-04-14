package org.example.spring_ai_learning;

import org.junit.jupiter.api.Test;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AiDemoApplicationTests {
@Autowired
    private OpenAiEmbeddingModel openAiEmbeddingModel;
@Test
    public void contextLoads() {
    String query="我是永雏塔菲";
    float[] queryVector=openAiEmbeddingModel.embed(query);
    System.out.println("查询向量:"+queryVector.length);
}
}
