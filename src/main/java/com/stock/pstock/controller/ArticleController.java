package com.stock.pstock.controller;

import com.stock.pstock.exception.RessourceNotfoundException;
import com.stock.pstock.model.Article;
import com.stock.pstock.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class ArticleController {
    @Autowired
    ArticleRepository articleRepository;
    @GetMapping("/articles")
    public List<Article> getAllArticle(){
        System.out.println("Get All Articles ...");
        List<Article> articles=new ArrayList<>();
         articleRepository.findAll().forEach(articles::add);
         return articles;
    }
    @GetMapping("/articles/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable(value = "id") Long ArticleId)
    throws RessourceNotfoundException{

            Article article = articleRepository.findById(ArticleId)
                    .orElseThrow(() -> new RessourceNotfoundException("Article not Found : " +ArticleId));
            return ResponseEntity.ok().body(article);
        }
        @PostMapping("/addArticle")
    public Article createArticle(@Validated @RequestBody Article article){
        return  articleRepository.save(article);
        }
    @DeleteMapping("/articles/{id}")
    public Map<String, Boolean> deleteArticle(@PathVariable(value = "id") Long ArticleId)
    throws RessourceNotfoundException{
        Article article =articleRepository.findById(ArticleId)
                .orElseThrow(() -> new RessourceNotfoundException("Article not Found : " + ArticleId));
        articleRepository.delete(article);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted" , Boolean.TRUE);
        return  response;
    }
    @PutMapping("/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable("id") long id ,@RequestBody Article article){
        System.out.println("Delete Article with ID = " +id);
        Optional <Article> articleInfo = articleRepository.findById(id);
        if(articleInfo.isPresent()){
            Article article1 =articleInfo.get();
            article1.setCode(article.getCode());
            article1.setLibelle((article.getLibelle()));
            article1.setFodec(article.getFodec());
            article1.setId_cat(article.getId_cat());
            article1.setId_scat(article.getId_scat());
            article1.setPrix_achat(article.getPrix_achat());
            article1.setPv(article.getPv());
            article1.setTva(article.getTva());
return  new ResponseEntity<>(articleRepository.save(article), HttpStatus.OK);}
    else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

