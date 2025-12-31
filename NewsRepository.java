package com.example.newsdb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.example.newsdb.model.News;

public interface NewsRepository extends JpaRepository<News, Integer> {

    // Derived query: Find news by category
    List<News> findByCategory(String category);

    // Derived query: Find news by year
    List<News> findByYear(int year);

    // Derived query: Find news by category and year
    List<News> findByCategoryAndYear(String category, int year);

    // JPQL query: Find news by title (partial match)
    @Query("SELECT n FROM News n WHERE n.title LIKE %:keyword%")
    List<News> searchByTitle(@Param("keyword") String keyword);

    // Native SQL query: Find news by category (using real SQL)
    @Query(value = "SELECT * FROM news WHERE category = ?1", nativeQuery = true)
    List<News> findByCategoryNative(String category);

    // Delete old news by year (custom query)
    @Transactional
    @Modifying
    @Query("DELETE FROM News n WHERE n.year < :year")
    int deleteOldNews(@Param("year") int year);
}
