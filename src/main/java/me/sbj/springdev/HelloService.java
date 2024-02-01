package me.sbj.springdev;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@RequiredArgsConstructor
public class HelloService {

    //private final HelloRepository helloRepository;
    @Autowired
    HelloRepository helloRepository;

    public List<Member> getAllMembers() {
        return helloRepository.findAll();
    }
}
