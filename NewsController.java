package com.example.newsdb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.newsdb.model.News;
import com.example.newsdb.service.NewsService;

@RestController
@RequestMapping("/news")
public class NewsController {

    private final NewsService service;

    public NewsController(NewsService service) {
        this.service = service;
    }

    // Endpoint to get all news
    @GetMapping
    public List<News> findAll() {
        return service.findAll();
    }

    // Endpoint to find news by ID
    @GetMapping("/{id}")
    public News findById(@PathVariable int id) {
        return service.findById(id);
    }

    // Endpoint to save news (create or update)
    @PostMapping
    public News save(@RequestBody News news) {
        return service.save(news);
    }

    // Endpoint to update news
    @PutMapping("/{id}")
    public News update(@PathVariable int id, @RequestBody News news) {
        news.setId(id);
        return service.save(news);
    }

    // Endpoint to delete news by ID
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        service.deleteById(id);
    }

    // Endpoint to find news by category
    @GetMapping("/category/{category}")
    public List<News> getByCategory(@PathVariable String category) {
        return service.findByCategory(category);
    }

    // Endpoint to find news by year
    @GetMapping("/year/{year}")
    public List<News> getByYear(@PathVariable int year) {
        return service.findByYear(year);
    }

    // Endpoint to find news by category and year
    @GetMapping("/search")
    public List<News> search(
            @RequestParam String category,
            @RequestParam int year) {
        return service.findByCategoryAndYear(category, year);
    }

    // Endpoint to search news by title (using JPQL)
    @GetMapping("/title")
    public List<News> searchByTitle(@RequestParam String keyword) {
        return service.searchByTitle(keyword);
    }

    // Endpoint to find news by category using Native SQL
    @GetMapping("/native/category")
    public List<News> findByCategoryNative(@RequestParam String category) {
        return service.findByCategoryNative(category);
    }

    // Endpoint to delete old news by year
    @DeleteMapping("/delete/old/{year}")
    public String deleteOldNews(@PathVariable int year) {
        int count = service.deleteOldNews(year);
        return count + " news articles deleted.";
    }
}
