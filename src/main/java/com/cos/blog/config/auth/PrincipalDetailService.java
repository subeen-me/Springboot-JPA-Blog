package com.cos.blog.config.auth;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service //Bean 등록
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //스프링이 로그인 요청을 가로챌 때 username, password 변수 2개를 가로채는데
    //password 부분 처리는 알아서 한다.
    //해야할 일은 해당 username이 db에 있는지만 여기서 확인해주면 된다.
    //이걸 만들어줘야 여기서 만든 User의 값을 사용할 수 있다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUsername(username)
                .orElseThrow(()->{ //optional 타입에서는 orElseThrow 처리
                    return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : "+username);
                });
        return new PrincipalDetail(principal); //시큐리티 세션에 유저 정보가 저장이 된다.
    }
}
