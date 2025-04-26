package com.turkmuhendisi.user.service;

import com.turkmuhendisi.user.dto.ArticleCreationDto;
import com.turkmuhendisi.user.model.Article;
import com.turkmuhendisi.user.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public Article createArticle(ArticleCreationDto from) {
        Article article = new Article(
                from.getTitle(),
                from.getContent(),
                from.getImageUrl(),
                from.getAuthor(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                0,
                from.getComments(),
                from.getCategory()
        );

        return articleRepository.save(article);
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }
}
