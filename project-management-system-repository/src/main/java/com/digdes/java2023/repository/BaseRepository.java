package com.digdes.java2023.repository;

public interface BaseRepository<T> {
    void save(T item);
    T find(String inputString);
}
