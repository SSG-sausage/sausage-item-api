package com.ssg.sausageitemapi.item.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ShppCd {

    SSG_SHPP("쓱배송"),
    EMART_TRADERS_SHPP("이마트 트레이더스 배송");

    private final String nm;
}
