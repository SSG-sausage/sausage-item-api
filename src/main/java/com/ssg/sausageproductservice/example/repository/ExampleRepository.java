package com.ssg.sausageproductservice.example.repository;

import com.ssg.sausageproductservice.example.entity.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepository extends JpaRepository<Example, Long>, ExampleRepositoryCustom {

}
