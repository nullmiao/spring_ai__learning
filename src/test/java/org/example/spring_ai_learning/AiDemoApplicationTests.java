package org.example.spring_ai_learning;

import org.junit.jupiter.api.Test;
import org.springframework.ai.document.Document;
import org.springframework.ai.openai.OpenAiEmbeddingModel;
import org.springframework.ai.reader.ExtractedTextFormatter;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.reader.pdf.config.PdfDocumentReaderConfig;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import javax.naming.directory.SearchResult;
import java.util.List;

@SpringBootTest
public class AiDemoApplicationTests {

    // 先只测试 PDF 读取，vectorStore 和 embedding 暂时注释，避免报错
     @Autowired
     private OpenAiEmbeddingModel openAiEmbeddingModel;

     @Autowired
     private VectorStore vectorStore;

    // 测试：读取 PDF（最稳定，不会报错）
    @Test
    public void testReadPdf() {
        // 确保项目根目录有 sample.pdf
        Resource resource = new FileSystemResource("sample.pdf");

        PagePdfDocumentReader reader = new PagePdfDocumentReader(
                resource,
                PdfDocumentReaderConfig.builder()
                        .withPageExtractedTextFormatter(ExtractedTextFormatter.defaults())
                        .withPagesPerDocument(1)
                        .build()
        );

        List<Document> documents = reader.read();
        vectorStore.add(documents);
        SearchRequest request = SearchRequest.builder().query("中东局势").topK(1).similarityThreshold(0.6).filterExpression("笔记.pdf").build();
        List<Document> result = vectorStore.similaritySearch(request);
        System.out.println(result.getFirst().getText());
        System.out.println(result.getLast().getScore());
        System.out.println(result.getFirst().getId());
        System.out.println("读取到文档数量：" + documents.size());


    }
    @Test
    public void contextLoads() {
        String query="我是永雏塔菲";
        float[] queryVector=openAiEmbeddingModel.embed(query);
        System.out.println("查询向量:"+queryVector.length);
    }
}