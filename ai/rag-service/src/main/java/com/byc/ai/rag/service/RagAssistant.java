package com.byc.ai.rag.service;

import dev.langchain4j.service.SystemMessage;

/**
 * LangChain4j AI service interface, implemented at runtime by {@link dev.langchain4j.service.AiServices}.
 */
public interface RagAssistant {
    
    @SystemMessage("""
            You are a helpful assistant. Always answer based on the retrieved context.
            If the context does not contain the answer, say "I don't know".
            Answer in the language of the user's question.
            """)
    String answer(String question);
}
