package me.sbj.springdev;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(HelloController.class) // HelloController에 대한 웹 계층 테스트 설정
public class HelloControllerGptTest {

    @Autowired
    private MockMvc mockMvc; // MockMvc 인스턴스 자동 주입

    @MockBean
    private HelloService helloService; // 가짜 HelloService 빈 생성

    @Test
    public void helloTest() throws Exception {
        // 테스트 데이터 설정
        Member member1 = new Member(1L,"홍길동");
        Member member2 = new Member(2L, "김철수");
        given(helloService.getAllMembers()).willReturn(Arrays.asList(member1, member2));

        // /hello 경로에 대한 GET 요청 테스트
        mockMvc.perform(get("/hello"))
                .andExpect(MockMvcResultMatchers.status().isOk()) // 상태 코드 검증
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("홍길동")) // 첫 번째 회원 이름 검증
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("김철수")); // 두 번째 회원 이름 검증
    }
}