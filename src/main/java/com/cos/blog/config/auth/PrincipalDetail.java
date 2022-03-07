package com.cos.blog.config.auth;

import com.cos.blog.model.User;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장을 해준다.
@Getter
public class PrincipalDetail implements UserDetails {

    private User user; //Composition. 객체를 품고있음

    public PrincipalDetail(User user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 계정이 만료되지 않았는지 리턴한다. (true:만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨있지 않는지 리턴한다. (true:잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호가 만료되지 않았는지 리턴한다. (true:만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화(사용가능한지)가 되어있는지 리턴한다. (true:활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }

    // 계정의 권한 목록을 리턴한다. (권한이 여러개 있을 수 있어서 루프를 돌아야 하는데 여기서는 한 개만)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collectors = new ArrayList<>(); //ArrayList도 컬렉션 타입.
        collectors.add(new GrantedAuthority() {
            @Override
            public String getAuthority() { //스프링에서 권한 ROLE을 받을 때, 앞에 ROLE_ 을 꼭 넣어줘야 한다. 규칙
                return "ROLE_"+user.getRole(); //ROLE_USER
            }
        });
        // 람다식 표현
        // collections.add(()->{return "ROLE_"+user.getRole();});

        return collectors;
    }
}