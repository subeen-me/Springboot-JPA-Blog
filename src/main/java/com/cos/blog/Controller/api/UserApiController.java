package com.cos.blog.Controller.api;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user){ //username, password, email
        System.out.println("UserApiController.save 호출됨");
        //이제 실제로 DB에 insert를 하고 아래에서 return이 되면 된다.
        user.setRole(RoleType.USER);
        userService.save(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


    /*
    // 이 방식 외 스프링 시큐리티 이용해서 로그인 할 것!
    //@PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
        System.out.println("UserApiController.login 호출됨 ");
        User principal = userService.login(user); //principal(접근주체)
        if(principal !=null) {
            session.setAttribute("principal", principal);
        }
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    } */

}
