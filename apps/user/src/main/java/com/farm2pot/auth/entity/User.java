package com.farm2pot.auth.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * packageName    : com.farm2pot.auth.entity
 * author         : TAEJIN
 * date           : 2025-10-03
 * description    :
 */
@Entity
@Table(name = "users")  // DB 테이블명
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User implements Serializable {
    private static final long serialVersionUID = 174726374856727L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 기본 PK

    @Column(nullable = false, unique = true)
    private String loginId;   // 사용자 아이디 (unique)

    @Column(nullable = false, unique = true)
    private String email;    // 이메일

    @Column(nullable = false)
    private String password; // 비밀번호 (bcrypt 암호화 저장)

    private String name;     // 이름

    private String loginType; // 로그인 방식 (local, google, kakao 등)

    private String phoneNo;   // 전화번호

    @Temporal(TemporalType.DATE)
    private Date birthDay;    // 생일

    private int status;       // 상태 (0=비활성, 1=활성 등)

    private String gender;    // 성별

    private String nickName;  // 닉네임

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "id"),
            foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT) // FK 제거
    )
    @Builder.Default
    @Column(name = "roles")
    private List<String> roles = new ArrayList<>();      // 권한 (ROLE_USER, ROLE_ADMIN 등)

}
