package me.sbj.springdev.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.sbj.springdev.domain.Article;
import me.sbj.springdev.dto.AddArticleRequest;
import me.sbj.springdev.dto.UpdateArticleRequest;
import me.sbj.springdev.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.xml.transform.Result;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogApiControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper; //직렬화 역직렬화를 위한 클래스

    @Autowired
    private WebApplicationContext context;

    @Autowired
    BlogRepository blogRepository;

    @BeforeEach //테스트 실행전 실행되는 메소드 설정 가능 여
    public void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        blogRepository.deleteAll(); //초기화 작업 수행
    }

    @DisplayName("블로그 글 추가에 성공한다.")
    @Test
    public void addArticle() throws Exception{

        //given
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";
        final AddArticleRequest userRequest = new AddArticleRequest(title, content);

        //객체 json으로 직렬화
        final String requestBody = objectMapper.writeValueAsString(userRequest);

        //when
        ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        //then
        result.andExpect(status().isCreated());

        List<Article> articles = blogRepository.findAll();

        assertThat(articles.size()).isEqualTo(1);
        assertThat(articles.get(0).getTitle()).isEqualTo(title);
        assertThat(articles.get(0).getContent()).isEqualTo(content);

    }

    @DisplayName("블로그 글 목록을 조회한다.")
    @Test
    public void findallArticles() throws Exception {
        //given - test 실행을 준비하는 단계
        final String url = "/api/articles";
        final String title = "title";
        final String content = "content";

        //AddArticleRequest request = new AddArticleRequest(title, content);
        Article article = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        //when -test 진행하는 단계
        ResultActions result = mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON));

        //then - test 결과를 검증하는 단계
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value(content))
                .andExpect(jsonPath("$[0].title").value(title));

    }

    @DisplayName("블로그 글 조회에 성공한다.")
    @Test
    public void findArticle() throws Exception {
        //given test준비하는 단계
        String url = "/api/articles/{id}";

        String title = "title";
        String content = "content";

        Article article = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        //when  test진행하는 단계
        ResultActions result = mockMvc.perform(get(url, article.getId())
                .contentType(MediaType.APPLICATION_JSON));

        //then  test 검증하는 단계
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(content))
                .andExpect(jsonPath("$.title").value(title));
    }

    @DisplayName("블로그 글 삭제에 성공한다.")
    @Test
    public void deleteArtile() throws Exception{
        //given test 준비하는 단계
        String url = "/api/articles/{id}";
        String title = "title";
        String content = "content";

        Article article = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        //when test 진행하는 단계
        mockMvc.perform(delete(url, article.getId()))
                .andExpect(status().isOk());

        //then test 검증하는 단계
        List<Article> articles = blogRepository.findAll();
        assertThat(articles.size()).isEqualTo(0);
    }

    @DisplayName("블로그 글 수정에 성공한다.")
    @Test
    public void updateArticle() throws Exception{
        //given test 준비하는 단계
        String url = "";
        String title = "title";
        String content = "content";
        String updateTitle = "updateTitle";
        String updateContent = "updateContent";

        Article article = blogRepository.save(Article.builder()
                .title(title)
                .content(content)
                .build());

        UpdateArticleRequest request = new UpdateArticleRequest(updateTitle, updateContent);

        //객체 json으로 직렬화
        final String requestBody = objectMapper.writeValueAsString(request);

        //when test 진행하는 단계
        ResultActions result = mockMvc.perform(put(url, article)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));

        //then test 검증하는 단계
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(updateContent))
                .andExpect(jsonPath("$.title").value(updateTitle));

    }
}