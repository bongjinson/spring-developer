package me.sbj.springdev.service;

import lombok.RequiredArgsConstructor;
import me.sbj.springdev.domain.Article;
import me.sbj.springdev.dto.AddArticleRequest;
import me.sbj.springdev.dto.UpdateArticleRequest;
import me.sbj.springdev.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor  //final키워드나 @NonNull 어노테이션이 붙은 필드에 대해 생성자 생성해줌.
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    public Article findById(Long id){
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id)); //orElseThrow는 예외를 발생시키는 메서드.
    }

    public void deleteById(Long id){
        blogRepository.deleteById(id);
    }

    public Article update(long id, UpdateArticleRequest request) {
        Article article  = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
        article.update(request.getTitle(), request.getContent());

        return article;
    }

}
