package com.carbackend.domain;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

//엔티티 만들면 리포지토리 만들어줘야함
@Entity
@Table(name="app_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter //왠만하면 엔티티에 세터는 쓰지 않는다.
@Builder
public class AppUser { //데이터 베이스에 User라고 들어가면 안 되니까 이름 다르게 해줘야한다

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false) //유일해야하고 널 안 된다
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role; //역할 권한
}
