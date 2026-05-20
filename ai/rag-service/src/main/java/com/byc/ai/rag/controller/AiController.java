package com.byc.ai.rag.controller;

import com.byc.ai.rag.service.IngestionService;
import com.byc.ai.rag.service.RagAssistant;
import com.byc.common.core.response.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/ai/rag")
public class AiController {
    
    private final RagAssistant assistant;
    
    private final IngestionService ingestion;
    
    public AiController(RagAssistant assistant, IngestionService ingestion) {
        this.assistant = assistant;
        this.ingestion = ingestion;
    }
    
    @PostMapping("/ask")
    public R<Map<String, Object>> ask(@RequestBody Map<String, String> body) {
        String question = body.getOrDefault("question", "");
        String answer = assistant.answer(question);
        return R.ok(Map.of("question", question, "answer", answer));
    }
    
    @PostMapping("/ingest")
    public R<Map<String, Object>> ingest(@RequestBody Map<String, String> body) {
        String text = body.getOrDefault("text", "");
        int chunks = ingestion.ingestText(text);
        return R.ok(Map.of("chunks", chunks));
    }
}
