package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

// DAO
// 자동으로 bean 등록이 된다. @Repository 어노테이션 생략 가능하다.
public interface UserRepository extends JpaRepository<User, Integer> {

    // SELECT * FROM user WHERE username = 1?;
    Optional<User> findByUsername(String username);

    //JPA Naming 전략
    //SELECT * FROM user WHERE username = ?(username) AND password = ?(password);
    // User findByUsernameAndPassword(String username, String password);

    // 네이밍 전략 말고 네이티브 쿼리로 이렇게 쓸 수도 있다.
    //@Query(value = "SELECT * FROM user WHERE username = ?1 AND password = ?2", nativeQuery = true)
    //User login(String username, String password);
}
