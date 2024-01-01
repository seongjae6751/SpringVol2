package com.api.MvcApi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity // 테이블과 링크될 클래스임을 나타낸다. camel case 이름을 언어스코어 네이밍으로 테이블 이름을 정함
@Data
public class User {
    @Id // 해당 테이블의 PK 필드를 나타낸다
    // @GeneratedValue // PK 생성 규칙을 나타냄 이 어노테이션을 추가시에 auto_increment가 된다.
    private String id;
    // @Column(name = DB에 있는 필드 이름) -> DB 테이블 빌드 명과 Entity클래스 필드 명이 다를 경우
    private String username;
    private String password;
}