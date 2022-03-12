package com.cos.blog.service;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //스프링이 컴포넌트 스캔을 통해서 bean에 등록을 해줌. IOC를 해준다.
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public void save(Board board, User user) { //title, content 2개를 받고, user를 받는다
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> boardList(Pageable pageable) { //List<Board>값을 리턴했지만 pageable이 들어오면서 Page타입으로 리턴된다.
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board viewContent(int id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다.");
                });
    }

    @Transactional
    public void delete(int id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Board requestboard) {
        Board board = boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
                }); //영속화 완료
        board.setTitle(requestboard.getTitle());
        board.setContent(requestboard.getContent());
        //해당 함수가 종료시(Service가 종료될 때) 트랜젝션이 종료. 이 때 더티체킹 - DB쪽으로 플러쉬(커밋)되어서 자동 업데이트가 된다.
    }

    @Transactional
    public void replySave(ReplySaveRequestDto replySaveRequestDto) {
        int result = replyRepository.rSave(replySaveRequestDto.getUserId(),
                replySaveRequestDto.getBoardId(),
                replySaveRequestDto.getContent());
        System.out.println(result); //오브젝트를 출력하게 되면 자동으로 toString()이 호출된다.
    }

    @Transactional
    public void replyDelete(int replyId) {
        replyRepository.deleteById(replyId);
    }
}
