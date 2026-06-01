package com.byc.ai.rag.service;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Factory configuration for creating RAG assistant instances.
 *
 * <p>Configures the AI assistant with chat language model, content retriever,
 * and chat memory for maintaining conversation context.
 */
@Configuration
public class AssistantFactory {

  /**
   * Creates a RagAssistant bean configured with chat model, content retrieval,
   * and conversation memory.
   *
   * <p>The assistant uses a message window chat memory with a maximum of 10 messages
   * to maintain conversation context across multiple interactions.
   *
   * @param chatModel the chat language model for generating responses
   * @param retriever the content retriever for fetching relevant context
   * @return a configured RagAssistant instance
   */
  @Bean
  public RagAssistant ragAssistant(ChatLanguageModel chatModel, ContentRetriever retriever) {
    return AiServices.builder(RagAssistant.class).chatLanguageModel(chatModel).contentRetriever(retriever)
        .chatMemory(MessageWindowChatMemory.withMaxMessages(10)).build();
  }
}
