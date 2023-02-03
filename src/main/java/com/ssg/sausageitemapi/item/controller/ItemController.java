package com.ssg.sausageitemapi.item.controller;

import com.ssg.sausageitemapi.common.dto.ErrorResponse;
import com.ssg.sausageitemapi.common.dto.SuccessResponse;
import com.ssg.sausageitemapi.common.success.SuccessCode;
import com.ssg.sausageitemapi.item.dto.request.ItemInvQtyValidateRequest;
import com.ssg.sausageitemapi.item.dto.response.ItemFindListResponse;
import com.ssg.sausageitemapi.item.dto.response.ItemFindResponse;
import com.ssg.sausageitemapi.item.service.ItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Item", description = "상품")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class ItemController {

    private final ItemService itemService;

    @Operation(summary = "[external] 전체 상품 리스트 조회", responses = {
            @ApiResponse(responseCode = "200", description = "전체 상품 리스트 조회 성공입니다.")
    })
    @GetMapping("/item")
    public ResponseEntity<SuccessResponse<ItemFindListResponse>> findAllItemList() {
        return SuccessResponse.success(SuccessCode.FIND_ITEM_SUCCESS, itemService.findAllItemList());
    }

    @Operation(summary = "[external] 단일 상품 조회", responses = {
            @ApiResponse(responseCode = "200", description = "단일 상품 조회 성공입니다."),
            @ApiResponse(responseCode = "404", description = "일치하는 상품 ID가 존재하지 않습니다.", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
    })
    @GetMapping(value = "/item/{itemId}")
    public ResponseEntity<SuccessResponse<ItemFindResponse>> findItem(@PathVariable Long itemId) {
        return SuccessResponse.success(SuccessCode.FIND_ITEM_SUCCESS, itemService.findItem(itemId));
    }

    @Operation(summary = "[internal] 상품 리스트 조회", responses = {
            @ApiResponse(responseCode = "200", description = "상품 리스트 조회 성공입니다.")
    })
    @GetMapping(value = "/item-list", params = "itemIdList")
    public ResponseEntity<SuccessResponse<ItemFindListResponse>> findItemList(@RequestParam List<Long> itemIdList) {
        return SuccessResponse.success(SuccessCode.FIND_ITEM_SUCCESS, itemService.findItemList(itemIdList));
    }

    @Operation(summary = "[internal] 상품 리스트 조회", responses = {
            @ApiResponse(responseCode = "200", description = "상품 재고 체크 성공입니다.")
    })
    @PostMapping(value = "/item/inv-qty-validation")
    public ResponseEntity<SuccessResponse<Boolean>> validateItemInvQty(
            @RequestBody ItemInvQtyValidateRequest itemInvQtyValidateRequest) {
        return SuccessResponse.success(SuccessCode.VALIDATE_ITEM_INV_QTY_SUCCESS,
                itemService.validateItemInvQty(itemInvQtyValidateRequest));
    }
}
