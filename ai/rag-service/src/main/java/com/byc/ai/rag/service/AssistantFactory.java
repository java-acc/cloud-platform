package com.byc.ai.rag.service;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AssistantFactory {
    
    @Bean
    public RagAssistant ragAssistant(ChatLanguageModel chatModel, ContentRetriever retriever) {
        return AiServices.builder(RagAssistant.class).chatLanguageModel(chatModel).contentRetriever(retriever)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10)).build();
    }
}
