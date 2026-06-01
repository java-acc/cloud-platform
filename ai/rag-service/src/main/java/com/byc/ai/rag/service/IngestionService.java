package com.byc.ai.rag.service;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Service for ingesting text documents into the vector embedding store.
 *
 * <p>Handles document splitting, embedding generation, and storage in the
 * vector database for later semantic retrieval.
 */
@Service
public class IngestionService {

  private final EmbeddingModel embeddingModel;

  private final EmbeddingStore<TextSegment> embeddingStore;

  private final DocumentSplitter splitter = DocumentSplitters.recursive(500, 50);

  /**
   * Constructs a new IngestionService with required dependencies.
   *
   * @param embeddingModel the model used to generate embeddings from text segments
   * @param embeddingStore the store where embeddings and text segments are persisted
   */
  public IngestionService(EmbeddingModel embeddingModel, EmbeddingStore<TextSegment> embeddingStore) {
    this.embeddingModel = embeddingModel;
    this.embeddingStore = embeddingStore;
  }

  /**
   * Ingests a text document by splitting it into segments, generating embeddings,
   * and storing them in the vector database.
   *
   * <p>The text is split using recursive splitting with 500 characters per segment
   * and 50 characters overlap between segments.
   *
   * @param text the raw text content to ingest
   * @return the number of text segments created and stored
   */
  public int ingestText(String text) {
    Document doc = Document.from(text);
    List<TextSegment> segments = splitter.split(doc);
    List<Embedding> embeddings = embeddingModel.embedAll(segments).content();
    embeddingStore.addAll(embeddings, segments);
    return segments.size();
  }
}
