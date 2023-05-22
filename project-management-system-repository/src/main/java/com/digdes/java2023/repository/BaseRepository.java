package com.digdes.java2023.repository;

public interface BaseRepository<T>  {
    T find(String inputString);
}
