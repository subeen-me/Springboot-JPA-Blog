package com.cos.blog.service;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service //스프링이 컴포넌트 스캔을 통해서 bean에 등록을 해줌. IOC를 해준다.
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void save(Board board, User user) { //title, content 2개를 받고, user를 받는다
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

}
