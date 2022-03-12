package com.cos.blog.Controller.api;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.BoardService;
import com.cos.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal){
        boardService.save(board, principal.getUser());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> deleteById(@PathVariable int id) {
        boardService.delete(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<Integer> updateById(@PathVariable int id, @RequestBody Board board) {
        System.out.println("BoardApiController : update : id : "+id);
        System.out.println("BoardApiController : update : board : "+board.getTitle());
        System.out.println("BoardApiController : update : board : "+board.getContent());
        System.out.println("updateController");
        boardService.update(id, board);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    //데이터를 받을 때 컨트롤러에서 dto를 만들어서 받는 게 좋다.
    //dto를 사용하지 않은 이유는
   // @PostMapping("/api/board/{boardId}/reply")
//    public ResponseDto<Integer> replySave(@PathVariable int boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principal){
//        boardService.replySave(principal.getUser(), boardId, reply);
//        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//    }

    //데이터를 받을 때 컨트롤러에서 dto 사용. 데이터를 한번에 받아서 영속화
    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<Integer> replySave(@RequestBody ReplySaveRequestDto replySaveRequestDto){
        boardService.replySave(replySaveRequestDto);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<Integer> replyDelete(@PathVariable int replyId) {
        boardService.replyDelete(replyId);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
