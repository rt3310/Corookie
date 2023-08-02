package com.fourttttty.corookie.member.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String email;

    @Embedded
    private Oauth2 oauth2;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Role> role = new ArrayList<>(List.of(Role.ROLE_USER));

    private Member(String name, String email, Oauth2 oauth2) {
        this.name = name;
        this.email = email;
        this.oauth2 = oauth2;
    }

    private Member(Long id,String name, String email, Oauth2 oauth2){
        this.id = id;
        this.name = name;
        this.email = email;
        this.oauth2 = oauth2;
    }

    public static Member of(String name, String email, Oauth2 oauth2) {
        return new Member(name, email, oauth2);
    }

    public static Member of(Long id, String name, String email, Oauth2 oauth2) {
        return new Member(id, name, email, oauth2);
    }

    public List<SimpleGrantedAuthority> getRole() {
        return role.stream()
                .map(Role::name)
                .map(SimpleGrantedAuthority::new)
                .toList();
    }
}
