package com.cos.blog.repository;

import com.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// DAO
// 자동으로 bean 등록이 된다. @Repository 어노테이션 생략 가능하다.
public interface UserRepository extends JpaRepository<User, Integer> {

}
