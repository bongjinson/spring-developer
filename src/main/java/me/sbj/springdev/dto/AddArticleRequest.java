package me.sbj.springdev.dto;

import lombok.*;
import me.sbj.springdev.domain.Article;

@NoArgsConstructor  //기본 생성자 추가
@AllArgsConstructor //모든 필드값을 파라미터로 받는 생성자 추가
@Getter
public class AddArticleRequest {
    private String title;
    private String content;

    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
