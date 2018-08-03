package com.xy365.web.controller;

import com.xy365.web.domain.form.ArticleForm;
import com.xy365.web.domain.form.UserForm;
import com.xy365.web.domain.support.SimpleResponse;
import com.xy365.web.service.ArticleService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

@RestController
@CrossOrigin("*")
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/list")
    public SimpleResponse findArticle(@PageableDefault(page = 0,size = 8,sort = {"top","createTime"},direction = Sort.Direction.DESC) Pageable page){
       return SimpleResponse.success(articleService.findArticle(page));
    }

    @GetMapping("/listOwner")
    public SimpleResponse listOwner(HttpServletRequest request,@PageableDefault(page = 0,size = 8,sort = {"createTime"},direction = Sort.Direction.DESC) Pageable page){
        return SimpleResponse.success(articleService.listOwnerTimeLine(request, page));
    }

    @GetMapping
    public SimpleResponse findArticleById(Long id){
        return SimpleResponse.success(articleService.findArticleById(id));
    }

    @PostMapping("/write")
    public DeferredResult<SimpleResponse> writeArticle(HttpServletRequest request, @RequestBody @Valid ArticleForm articleForm, BindingResult bindingResult){
        DeferredResult<SimpleResponse> deferredResult = new DeferredResult<>();

        CompletableFuture.supplyAsync(()->
                articleService.writeArticle(request,articleForm)
        ).whenCompleteAsync((result, throwable) ->
                deferredResult.setResult(SimpleResponse.success())
        );
        return deferredResult;
    }

    @PostMapping("/views")
    public DeferredResult<SimpleResponse> viewsArticle(String articleId){
        DeferredResult<SimpleResponse> deferredResult = new DeferredResult<>();
        CompletableFuture.supplyAsync(()->articleService.viewsArticle(articleId))
                .whenCompleteAsync((result, throwable) -> deferredResult.setResult(SimpleResponse.success()));
       return deferredResult;
    }

    @PostMapping("/fabulous")
    public DeferredResult<SimpleResponse> fabulousArticle(String articleId){
        DeferredResult<SimpleResponse> deferredResult = new DeferredResult<>();
        CompletableFuture.supplyAsync(()->articleService.fabulousArticle(articleId))
                .whenCompleteAsync((result, throwable) -> deferredResult.setResult(SimpleResponse.success()));
        return deferredResult;
    }

    @PostMapping("/unfabulous")
    public DeferredResult<SimpleResponse> unfabulousArticle(String articleId){
        DeferredResult<SimpleResponse> deferredResult = new DeferredResult<>();
        CompletableFuture.supplyAsync(()->articleService.unfabulousArticle(articleId))
                .whenCompleteAsync((result, throwable) -> deferredResult.setResult(SimpleResponse.success()));
        return deferredResult;
    }


    @GetMapping("/countArticle")
    public SimpleResponse countArticleByUser(HttpServletRequest request){
        return SimpleResponse.success(articleService.countArticleByUser(request));
    }


    @PostMapping("/remove")
    public SimpleResponse remove(HttpServletRequest request,@RequestBody ArticleForm articleForm){
       try {
           return SimpleResponse.success(articleService.removeByArticleId(request,articleForm.getArticleId()));
       }catch (Exception e){
           return SimpleResponse.error(e.getMessage());
       }
    }

}
