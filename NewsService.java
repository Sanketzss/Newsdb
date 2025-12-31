package com.example.newsdb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.newsdb.model.News;
import com.example.newsdb.repository.NewsRepository;

@Service
public class NewsService {

    private final NewsRepository repository;

    public NewsService(NewsRepository repository) {
        this.repository = repository;
    }

    // Method to get all news
    public List<News> findAll() {
        return repository.findAll();
    }

    // Method to find news by ID
    public News findById(int id) {
        return repository.findById(id).orElse(null);
    }

    // Method to save news (create or update)
    public News save(News news) {
        return repository.save(news);
    }

    // Method to delete news by ID
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    // Method to find news by category
    public List<News> findByCategory(String category) {
        return repository.findByCategory(category);
    }

    // Method to find news by year
    public List<News> findByYear(int year) {
        return repository.findByYear(year);
    }

    // Method to find news by category and year
    public List<News> findByCategoryAndYear(String category, int year) {
        return repository.findByCategoryAndYear(category, year);
    }

    // Method to search news by title using JPQL
    public List<News> searchByTitle(String keyword) {
        return repository.searchByTitle(keyword);
    }

    // Method to find news by category using Native SQL
    public List<News> findByCategoryNative(String category) {
        return repository.findByCategoryNative(category);
    }

    // Method to delete old news by year
    public int deleteOldNews(int year) {
        return repository.deleteOldNews(year);
    }
}
