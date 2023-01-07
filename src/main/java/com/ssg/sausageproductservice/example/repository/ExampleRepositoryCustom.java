package com.ssg.sausageproductservice.example.repository;

import com.ssg.sausageproductservice.example.entity.Example;

public interface ExampleRepositoryCustom {

    Example findExampleById(Long id);
}
