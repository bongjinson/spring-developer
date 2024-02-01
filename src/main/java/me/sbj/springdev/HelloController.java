package me.sbj.springdev;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequiredArgsConstructor
public class HelloController {

    @Autowired
    HelloService helloService;
    //private final HelloService helloService;

    @GetMapping("/hello")
    public List<Member> hello(){
        List<Member> members = helloService.getAllMembers();
        return members;
    }
}
