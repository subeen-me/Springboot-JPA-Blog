package com.cos.blog.test;

import com.cos.blog.model.Reply;
import org.junit.Test;


public class ReplyObjectTest {

    @Test
    public void toStringTest() {
        Reply reply = Reply.builder()
                .id(1)
                .user(null)
                .content("안녕")
                .build();

        System.out.println(reply);
    }
}
