package com.cos.blog.test;

import org.springframework.web.bind.annotation.*;

//@Controller : 사용자가 요청하면 응답(HTML 파일)
@RestController //사용자가 요청하면 응답(Data)을 할 수 있다
public class HttpControllerTest {

    //http://localhost:8080/http/get (select) 쿼리스트링방식
    @GetMapping("/http/get")
    public String getTest(Member m) { //http://localhost:8080/http/get?id=1&password=123&username=susoo&email=ssar@nate.com
        return "get 요청 : " +m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
    }

    @PostMapping("/http/post") //(insert) @RequestBody 어노테이션으로 바디로 받는다 text/plain, application/json
    public String postTest(@RequestBody Member m) { //MessageConverter가 json타입을 파싱해서 객체로 받는다
        return "post 요청 : " +m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
    }

    @PutMapping("/http/put") //(update)
    public String putTest(@RequestBody Member m) {
        return "put 요청 : " +m.getId() + "," + m.getUsername() + "," + m.getPassword() + "," + m.getEmail();
    }

    @DeleteMapping("/http/delete") //(delete)
    public String deleteTest() {
        return "delete 요청";
    }

}
