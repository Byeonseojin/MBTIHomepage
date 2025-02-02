package org.example.mbtihomepage.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "Community")
public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long rno;

    private String text;

    private String replyer;
    // 지연로딩 방식 수정 이유: 즉시로딩을 사용할 경우 불필요한 Join을 하므로 성능저하시킬 수 있다.
    @ManyToOne(fetch = FetchType.LAZY)
    private Community community; //Foreign key 설정(참조무결성 유지)
}
