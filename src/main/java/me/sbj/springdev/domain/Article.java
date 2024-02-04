package me.sbj.springdev.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)      //기본 생성자 추가 (파라미터 없는 생성자) protected
@Getter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType .IDENTITY) // 자동 생성 (DB에서 자동으로 생성해줌)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false) // 컬럼 설정 시 null 값 허용 안함. 컬럼 설정 시 null 값
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder
    public Article(String title, String content){
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

    /*protected Article() {
    }

    public Long getId() {
        return id;
    }

    public java.lang.String getTitle() {
        return title;
    }

    public java.lang.String getContent() {
        return content;
    }*/
}
