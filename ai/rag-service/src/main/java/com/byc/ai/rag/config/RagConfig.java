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

/**
 * Configuration class for RAG (Retrieval-Augmented Generation) components.
 *
 * <p>Configures the embedding store using PGVector and sets up the content retriever
 * for semantic search with similarity scoring.
 */
@Configuration
public class RagConfig {

  /**
   * Creates a PGVector-based embedding store for storing text segment embeddings.
   *
   * @param host the PostgreSQL database host
   * @param port the PostgreSQL database port
   * @param database the database name
   * @param user the database username
   * @param password the database password
   * @param table the table name for storing embeddings
   * @param dimension the vector dimension for embeddings
   * @return a configured PgVectorEmbeddingStore instance
   */
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

  /**
   * Creates a content retriever that performs semantic search on the embedding store.
   *
   * <p>The retriever is configured to return top 5 results with a minimum similarity
   * score of 0.7 to ensure relevant context retrieval.
   *
   * @param store the embedding store containing vectorized text segments
   * @param embeddingModel the model used to generate query embeddings
   * @return a configured ContentRetriever instance
   */
  @Bean
  public ContentRetriever contentRetriever(EmbeddingStore<TextSegment> store, EmbeddingModel embeddingModel) {
    return EmbeddingStoreContentRetriever.builder().embeddingStore(store).embeddingModel(embeddingModel)
        .maxResults(5).minScore(0.7).build();
  }
}
