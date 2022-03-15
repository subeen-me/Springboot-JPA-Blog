package com.cos.blog.repository;

import com.cos.blog.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    @Modifying
    @Query("update Board b set b.count = b.count + 1 where b.id = :id")
    int updateCount(int id);
}
