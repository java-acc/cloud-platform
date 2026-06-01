package com.byc.ai.rag.controller;

import com.byc.ai.rag.service.IngestionService;
import com.byc.ai.rag.service.RagAssistant;
import com.byc.common.core.response.R;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for AI RAG (Retrieval-Augmented Generation) operations.
 *
 * <p>Provides endpoints for asking questions with context retrieval and ingesting
 * text documents into the vector database for semantic search.
 */
@RestController
@RequestMapping("/api/ai/rag")
public class AiController {

  private final RagAssistant assistant;

  private final IngestionService ingestion;

  /**
   * Constructs a new AiController with required dependencies.
   *
   * @param assistant the RAG assistant for answering questions
   * @param ingestion the ingestion service for processing text documents
   */
  public AiController(RagAssistant assistant, IngestionService ingestion) {
    this.assistant = assistant;
    this.ingestion = ingestion;
  }

  /**
   * Answers a question using RAG with retrieved context from the vector database.
   *
   * @param body the request body containing the question field
   * @return a response containing the question and generated answer
   */
  @PostMapping("/ask")
  public R<Map<String, Object>> ask(@RequestBody Map<String, String> body) {
    String question = body.getOrDefault("question", "");
    String answer = assistant.answer(question);
    return R.ok(Map.of("question", question, "answer", answer));
  }

  /**
   * Ingests text content by splitting it into segments and storing embeddings.
   *
   * @param body the request body containing the text field to ingest
   * @return a response containing the number of text chunks created
   */
  @PostMapping("/ingest")
  public R<Map<String, Object>> ingest(@RequestBody Map<String, String> body) {
    String text = body.getOrDefault("text", "");
    int chunks = ingestion.ingestText(text);
    return R.ok(Map.of("chunks", chunks));
  }
}
