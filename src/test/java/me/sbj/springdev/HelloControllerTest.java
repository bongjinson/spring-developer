package me.sbj.springdev;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest //테스트용 애플리케이션 컨텍스트 생성
@AutoConfigureMockMvc //mockMvc생성
class HelloControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private HelloRepository helloRepository;

    @BeforeEach //테스트 실행 전 실행하는 메서드
    public void mockMvoSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @AfterEach //테스트 실행 후 실행하는 메서드
    public void cleanUp(){
        helloRepository.deleteAll();
    }

    /*
    * given - when - then패턴
    * given 테스트 실행을 준비하는 단계
    * when 테스트를 진행하는 단계
    * then 결과를 검증하는 단계
    * */

    @DisplayName("getAllMembers: 아티클 조회에 성공한다.")
    @Test
    public void getAllMembers() throws Exception {
        //given
        final String url = "/hello";
        Member savedMember = helloRepository.save(new Member(1L, "손봉진"));

        //when
        final ResultActions result = mockMvc.perform(get(url)
                .accept(MediaType.APPLICATION_JSON));

        //then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(savedMember.getId()))
                .andExpect(jsonPath("$[0].name").value(savedMember.getName()));
    }

    /*public void saveMenuTest(){
        //given
        final String name = "아메리카노";
        final int price = 2000;

        //when
        final Menu americano = new Menu(name, price);

        //then
        final Menu saveMenu = menuService.findById(savedId).get();
        assertThat(saveMenu.getName()).isEqualTo(name);
        assertThat(saveMenu.getPrice()).isEqualTo(price);
    }*/

}

