package org.example.spring_ai_learning.tools;

import org.apache.logging.log4j.message.AsynchronouslyFormattable;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class  CourseTools<T,Y> {
    @Tool(description = "查询课程")
    public List<T> queryCourse(Y query){

        return List.of();
    }
}
