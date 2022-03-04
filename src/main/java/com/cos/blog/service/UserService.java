package com.cos.blog.service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service //스프링이 컴포넌트 스캔을 통해서 bean에 등록을 해줌. IOC를 해준다.
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public int save(User user) {
        try {
            userRepository.save(user);
            return 1;
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("UserService : save() : "+e.getMessage());
        }
        return -1;
    }
}
