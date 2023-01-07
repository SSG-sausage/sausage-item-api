package com.ssg.sausageproductservice.example.controller;

import com.ssg.sausageproductservice.common.dto.ErrorResponse;
import com.ssg.sausageproductservice.common.dto.SuccessResponse;
import com.ssg.sausageproductservice.common.success.SuccessCode;
import com.ssg.sausageproductservice.example.dto.request.ExampleRequest;
import com.ssg.sausageproductservice.example.dto.response.ExampleResponse;
import com.ssg.sausageproductservice.example.service.ExampleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Example", description = "이그젬플")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ExampleController {

    private final ExampleService exampleService;

    @Operation(summary = "포스트 생성", responses = {
            @ApiResponse(responseCode = "200", description = "생성 성공입니다."),
            @ApiResponse(responseCode = "400", description = "400 에러", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @PostMapping("/post")
    public ResponseEntity<SuccessResponse<ExampleResponse>> create(@RequestBody ExampleRequest request) {
        return SuccessResponse.success(SuccessCode.CREATED_SUCCESS, exampleService.create(request));
    }
}
