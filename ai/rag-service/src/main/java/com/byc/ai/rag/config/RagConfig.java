package com.byc.ai.rag.config;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pgvector.PgVectorEmbeddingStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RagConfig {
    
    @Bean
    public EmbeddingStore<TextSegment> embeddingStore(@Value("${app.ai.vectorstore.pg.host:127.0.0.1}") String host,
            @Value("${app.ai.vectorstore.pg.port:5432}") int port,
            @Value("${app.ai.vectorstore.pg.database:rag}") String database,
            @Value("${app.ai.vectorstore.pg.user:postgres}") String user,
            @Value("${app.ai.vectorstore.pg.password:postgres}") String password,
            @Value("${app.ai.vectorstore.pg.table:embeddings}") String table,
            @Value("${app.ai.vectorstore.pg.dimension:1536}") int dimension) {
        return PgVectorEmbeddingStore.builder().host(host).port(port).database(database).user(user).password(password)
                .table(table).dimension(dimension).createTable(true).build();
    }
    
    @Bean
    public ContentRetriever contentRetriever(EmbeddingStore<TextSegment> store, EmbeddingModel embeddingModel) {
        return EmbeddingStoreContentRetriever.builder().embeddingStore(store).embeddingModel(embeddingModel)
                .maxResults(5).minScore(0.7).build();
    }
}
