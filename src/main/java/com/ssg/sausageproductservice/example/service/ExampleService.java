package com.ssg.sausageproductservice.example.service;

import com.ssg.sausageproductservice.example.dto.request.ExampleRequest;
import com.ssg.sausageproductservice.example.dto.response.ExampleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExampleService {

    public ExampleResponse create(ExampleRequest request) {
        return ExampleResponse.of();
    }
}
