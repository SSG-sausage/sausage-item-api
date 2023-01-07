package com.ssg.sausageproductservice.example.repository;

import static com.ssg.sausageproductservice.example.entity.QExample.example;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssg.sausageproductservice.example.entity.Example;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExampleRepositoryImpl implements ExampleRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Example findExampleById(Long id) {
        return queryFactory
                .selectFrom(example)
                .where(
                        example.id.eq(id)
                )
                .fetchOne();
    }
}
