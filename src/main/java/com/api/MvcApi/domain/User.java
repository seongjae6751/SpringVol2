package com.api.MvcApi.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity // 테이블과 링크될 클래스임을 나타낸다. camel case 이름을 언어스코어 네이밍으로 테이블 이름을 정함
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    @Id // 해당 테이블의 PK 필드를 나타낸다
    // @GeneratedValue // PK 생성 규칙을 나타냄 이 어노테이션을 추가시에 auto_increment가 된다.
    private String id;
    // @Column(name = DB에 있는 필드 이름) -> DB 테이블 빌드 명과 Entity클래스 필드 명이 다를 경우
    private String username;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "roles")
    private List<String> roles = new ArrayList<>();

    // 권한 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        // return List.of(new SimpleGrantedAuthority("user"));
    }

    // 사용자 이름 반환
    @Override
    public String getUsername() {
        return username;
    }

    //계정 만료 여부 반환
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠금 여부 반환
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 패스워드 만료 여부 반환
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 사용 가능 여부 반환
    @Override
    public boolean isEnabled() {
        return true;
    }
}