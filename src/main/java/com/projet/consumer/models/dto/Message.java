package com.projet.consumer.models.dto;

public interface Message<T> {
    T getPayload();
    String getEventType();
}
