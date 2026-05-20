package com.byc.ai.rag.service;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.data.document.splitter.DocumentSplitters;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngestionService {
    
    private final EmbeddingModel embeddingModel;
    
    private final EmbeddingStore<TextSegment> embeddingStore;
    
    private final DocumentSplitter splitter = DocumentSplitters.recursive(500, 50);
    
    public IngestionService(EmbeddingModel embeddingModel, EmbeddingStore<TextSegment> embeddingStore) {
        this.embeddingModel = embeddingModel;
        this.embeddingStore = embeddingStore;
    }
    
    public int ingestText(String text) {
        Document doc = Document.from(text);
        List<TextSegment> segments = splitter.split(doc);
        List<Embedding> embeddings = embeddingModel.embedAll(segments).content();
        embeddingStore.addAll(embeddings, segments);
        return segments.size();
    }
}
