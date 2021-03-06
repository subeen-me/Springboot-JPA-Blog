package com.cos.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

//ORM : Java(다른 언어도) Object를 테이블로 매핑해주는 기술
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴
@Entity //User 클래스가 자동으로 MySQL에 테이블이 생성이 된다.
//@DynamicInsert //insert 시에 Default 필드에 null이 들어가는걸 방지하고 default로 설정한 값이 들어가게 하는 어노테이션
public class User {

    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다
    private int id; //시퀀스, auto_increment

    @Column(nullable = false, length = 100, unique = true)
    private String username;

    @Column(nullable = false, length = 100) // 123456 =>해쉬(비밀번호 암호화) //카카오 로그인 이용하면 앞으로 패스워드가 null이 될 수도 있다. nullable=false 지움
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    //@ColumnDefault("user") + 클래스 어노테이션 @DynamicInsert
    //DB는 RoleType이 없기 때문에 해당 Enum이 String이라는걸 알려주는 어노테이션
    @Enumerated(EnumType.STRING)
    private RoleType role; //Enum을 쓰는게 좋다. enum은 도메인(범위)을 만들 때 좋다 // UDMIN, USER, manager

    @Column(nullable = true)
    private String oauth; //kakao,google

    @CreationTimestamp // 시간이 자동 입력
    private Timestamp createDate;
}
