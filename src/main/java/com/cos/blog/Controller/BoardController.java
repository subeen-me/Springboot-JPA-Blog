package com.cos.blog.Controller;

import com.cos.blog.config.auth.PrincipalDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    // @AuthenticationPrincipal PrincipalDetail principal
    @GetMapping({"","/"}) // 메인페이지로 갈 때는 인증이 필요없음
    public String index() { // 컨트롤러에서 세션 접근
        // /WEB-INF/views/index.jsp
        return "index";
    }

    //USER 권한이 필요
    @GetMapping("/board/saveForm")
    public String saveForm() {
        return "board/saveForm";
    }
}
