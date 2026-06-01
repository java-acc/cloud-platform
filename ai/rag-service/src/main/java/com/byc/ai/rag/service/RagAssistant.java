package com.byc.ai.rag.service;

import dev.langchain4j.service.SystemMessage;

/**
 * LangChain4j AI service interface for RAG-based question answering.
 *
 * <p>This interface is implemented at runtime by {@link dev.langchain4j.service.AiServices}
 * to provide context-aware responses using retrieved document segments.
 */
public interface RagAssistant {

  /**
   * Answers a question using retrieved context from the vector database.
   *
   * <p>The system message instructs the assistant to answer based on retrieved context
   * and respond in the same language as the user's question. If the context does not
   * contain sufficient information, the assistant responds with "I don't know".
   *
   * @param question the user's question to answer
   * @return the generated answer based on retrieved context
   */
  @SystemMessage(
      """
      You are a helpful assistant. Always answer based on the retrieved context.
      If the context does not contain the answer, say "I don't know".
      Answer in the language of the user's question.
      """)
  String answer(String question);
}
