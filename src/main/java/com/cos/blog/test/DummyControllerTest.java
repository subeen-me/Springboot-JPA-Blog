package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.EmptyStackException;
import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired //의존성 주입(DI)
    private UserRepository userRepository;

    @DeleteMapping("dummy/user/{id}")
    public String delete(@PathVariable int id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
        }
        return "삭제되었습니다. id = " + id;
    }

    /**
     * save 함수는 id를 전달하지 않으면 insert를 해주고,
     * save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 Update를 해주고
     * save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 한다.
     */
    //email, password
    @Transactional // 함수 종료 시에 자동 commit이 된다. save를 하지 않아도 update 된다. 더티 체킹
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
        System.out.println("id : " + id);
        System.out.println("password : "+requestUser.getPassword());
        System.out.println("email : "+requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("수정에 실패하였습니다.");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());
        // userRepository.save(user);
        //더티 체킹
        return null;
    }

    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    // 한 페이지 당 2건의 데이터를 리턴받아 볼 예정
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size=2, sort = "id", direction = Sort.Direction.DESC)Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);
        List<User> users = pagingUser.getContent();
        return users;
    }

    // {id} 주소로 파라미터를 전달받을 수 있다.
    // http://localhost:8080/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        // user/5를 찾았는데 데이터베이스에서 못찾게 되면 user가 null이 된다.
        // 그럼 return null이 되므로 프로그램에 문제가 생긴다.
        // Optional로 User객체를 감싸서 가져오고, null인지 아닌지 판단해서 return
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다. id : "+id);
            }
        });
        //user 객체 = 자바 오브젝트
        //변환(웹브라우저가 이해할 수 있는 데이터) -> json(Gson 라이브러리)
        //스프링부트는 MessageConverter 가 응답시에 자동 작동.
        //만약 자바 오브젝트를 리턴하게 되면 MessageConverter 가 Jackson 라이브러리를 호출해서
        //User 오브젝트를 json으로 변환해서 브라우저에게 던져준다.
        return user;
    }

    // http://localhost:8080/blog/dummy/join(요청)
    // http의 body에 username, password, email 데이터를 가지고 요청
    //  @PostMapping("/dummy/join")
    public String join0(String username, String password, String email) { // key=value (약속된 규칙)
        System.out.println("username : "+username);
        System.out.println("password : "+password);
        System.out.println("email : "+email);
        return "회원가입이 완료되었습니다";
    }

    @PostMapping("/dummy/join")
    public String join(User user) { // key=value (약속된 규칙)
        System.out.println("id : "+user.getId());
        System.out.println("username : "+user.getUsername());
        System.out.println("password : "+user.getPassword());
        System.out.println("email : "+user.getEmail());
        System.out.println("role : "+user.getRole());
        System.out.println("createDate : "+user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다";
    }
}
