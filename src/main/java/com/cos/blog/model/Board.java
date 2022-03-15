package com.cos.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob //대용량 데이터
    private String content; //섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨.

    @Column(columnDefinition = "integer default 0")
    private int count; //조회수. @ColumnDefault("0") 해서 default를 0으로 할 수도 있지만 여기서는 서비스에 대신 넣어준다 .

    /**
     * ManyToOne
     * Many=Board, User=One 한 명의 유저는 여러개의 게시글을 쓸 수 있다.
     * 여러개의 게시글은 한 명의 유저에 의해 쓰일 수 있다.
     * fetch = FetchType.EAGER : 보드를 가지고 올 때 user정보는 무조건 가져온다.
     * fetch = FetchType.LAZY : 무조건 가져오지 않고 필요할 때 가져온다.
     */
    @ManyToOne(fetch = FetchType.EAGER) //연관관계 Many=Board, User=One
    @JoinColumn(name = "userId") //외래키
    private User user; //DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

    // mappedBy 뒤에는 필드이름을 적는다.
    //mappedBy는 연관관계의 주인이 아니다. (FK가 아니므로 컬럼을 만들지 않는다)
    //CascadeType.Persist 옵션을 넣으면 엔티티를 영속화할 때 연관된 엔티티도 함께 영속화된다. board 오브젝트를 넣을 때 replys를 담아서 저장한다.
    //CascadeType.Remove 옵션은 게시글(board)를 지울 때 댓글도 같이 지울 수 있게 한다. 엔티티를 삭제하면 연관된 엔티티도 삭제된다.
    //CascadeType.All은 Persist와 Remove를 한꺼번에 다 넣은 옵션.
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"board","user"}) //무한참조방지
    @OrderBy("id desc") //내림차순 정렬
    public List<Reply> replys;

    @CreationTimestamp //데이터가 들어갈 때 자동으로 현재시간이 import 된다
    private Timestamp createDate;
}
