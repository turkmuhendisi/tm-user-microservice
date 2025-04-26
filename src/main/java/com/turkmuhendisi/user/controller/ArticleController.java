package com.turkmuhendisi.user.controller;

import com.turkmuhendisi.user.dto.ArticleCreationDto;
import com.turkmuhendisi.user.model.Article;
import com.turkmuhendisi.user.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/create-article")
    public ResponseEntity<Article> createArticle(@RequestBody ArticleCreationDto request) {
        return ResponseEntity.ok(articleService.createArticle(request));
    }

    @GetMapping("/get-all-articles")
    public ResponseEntity<List<Article>> getAllArticles() {
        return ResponseEntity.ok(articleService.getAllArticles());
    }
}
