package com.bloggingapp.bloggingapp.articles;


import com.bloggingapp.bloggingapp.articles.dtos.UpdateArticleRequest;
import com.bloggingapp.bloggingapp.users.UsersRepository;
import com.bloggingapp.bloggingapp.users.UsersService;
import org.springframework.stereotype.Service;
import com.bloggingapp.bloggingapp.articles.dtos.CreateArticleRequest;

@Service
public class ArticlesService {
    private final ArticlesRepository articlesRepository;
    private UsersRepository usersRepository;

    public ArticlesService(ArticlesRepository articlesRepository, UsersRepository usersRepository) {
        this.articlesRepository = articlesRepository;
        this.usersRepository = usersRepository;
    }

    //get all articles
    public Iterable<ArticleEntity> getAllArticles(){
        return articlesRepository.findAll();
    }

    //get article by its slug
    public ArticleEntity getArticleBySlug(String slug){
        var article = articlesRepository.findBySlug(slug);
        if(article == null){
            throw  new ArticleNotFoundException(slug);
        }
        //TODO: match password

        return article;
    }

    //create an article
    public ArticleEntity createArticle(CreateArticleRequest a, Long authorId){
         var author = usersRepository.findById(authorId).orElseThrow();

        return articlesRepository.save(ArticleEntity.builder()
                        .title(a.getTitle())
                        //Todo: create a proper slugification function
                        .slug(a.getTitle().toLowerCase().replaceAll("\\s+","-"))
                        .subtitle(a.getSubtitle())
                        .body(a.getBody())
                        .author(author)
                        .build()
                );
    }

    public ArticleEntity updateArticle(long articleId, UpdateArticleRequest u){
        var article = articlesRepository.findById(articleId).orElseThrow();

        if(u.getTitle() != null) {
            article.setTitle(u.getTitle());
            article.setSlug(u.getTitle().toLowerCase().replaceAll("\\s+","-"));
        }
        if(u.getSubtitle() != null)
            article.setSubtitle(u.getSubtitle());
        if(u.getBody() != null)
            article.setBody(u.getBody());

        return articlesRepository.save(article);
    }

    static class ArticleNotFoundException extends IllegalArgumentException{
        public ArticleNotFoundException(String slug){
            super("Article: "+slug+" not found ");
        }
    }
}
