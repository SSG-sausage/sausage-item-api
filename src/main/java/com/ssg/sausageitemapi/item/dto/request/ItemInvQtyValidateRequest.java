package com.ssg.sausageitemapi.item.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PUBLIC)
public class ItemInvQtyValidateRequest {

    List<ItemInfo> itemInfoList = new ArrayList<>();

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder(access = AccessLevel.PRIVATE)
    public static class ItemInfo {

        @Schema(description = "상품 ID")
        private Long itemId;

        @Schema(description = "공유장바구니임시주문상품 ID")
        private Long cartShareTmpOrdItemId;

        @Schema(description = "상품 수량")
        private Integer itemInvQty;
    }

}
