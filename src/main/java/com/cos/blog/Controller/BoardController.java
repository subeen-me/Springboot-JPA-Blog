package com.cos.blog.Controller;

import com.cos.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    // 컨트롤러에서 세션 접근
    // @AuthenticationPrincipal PrincipalDetail principal
    @GetMapping({"","/"})
    public String index(Model model, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC)Pageable pageable) { //spring에서 데이터를 가져갈 때는 model이 필요하다.
        // /WEB-INF/views/index.jsp
        model.addAttribute("boards", boardService.boardList(pageable));
        return "index"; //viewResolver 작동하면 해당 model의 정보를 들고 이동한다.
    }

    @GetMapping("/board/{id}")
    public String findById(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.viewContent(id));
        boardService.updateCount(id); //viewCount++
        return "board/detail";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model) {
        System.out.println("BoardController-updateForm");
        model.addAttribute("board", boardService.viewContent(id));
        return "board/updateForm";
    }

    //USER 권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }
}
