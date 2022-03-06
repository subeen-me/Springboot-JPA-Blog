package com.cos.blog.service;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service //스프링이 컴포넌트 스캔을 통해서 bean에 등록을 해줌. IOC를 해준다.
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public void save(User user) {
        String rawPassword = user.getPassword(); //1234 원본
        String encPassword = encoder.encode(rawPassword); //해쉬
        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

    /*
    @Transactional(readOnly = true) //Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성)
    public User login(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    } */
}
