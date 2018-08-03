package com.xy365.web.service;
import com.xy365.core.service.XyService;
import com.xy365.web.domain.form.ArticleForm;
import org.springframework.data.domain.Pageable;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ArticleService extends XyService {

   Map<String,Object> findArticle(Pageable pageable);

   Map<String,Object> listOwnerTimeLine(HttpServletRequest request,Pageable pageable);

   Map<String,Object>   writeArticle(HttpServletRequest request, ArticleForm articleForm);

   Map<String,Object> findArticleById(long articleId);

   Map<String,Object> viewsArticle(String articleId);

   Map<String,Object> fabulousArticle(String articleId);

   Map<String,Object> unfabulousArticle(String articleId);

   Map<String,Object> countArticleByUser(HttpServletRequest request);

   Map<String,Object> removeByArticleId(HttpServletRequest request,long articleId);


}
