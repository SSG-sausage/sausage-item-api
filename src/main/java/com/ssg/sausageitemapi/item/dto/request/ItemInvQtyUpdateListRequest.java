package com.ssg.sausageitemapi.item.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class ItemInvQtyUpdateListRequest {

    private List<ItemInfo> cartShareOrdItemInfoList = new ArrayList<>();

    private ItemInvQtyUpdateListDtoUpdateType updateType;


    @RequiredArgsConstructor
    @Getter
    public enum ItemInvQtyUpdateListDtoUpdateType {

        INCREASE(1),
        DECREASE(-1);

        private final int value;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder(access = AccessLevel.PRIVATE)
    public static class ItemInfo {

        @Schema(description = "상품 ID")
        private Long itemId;

        @Schema(description = "상품 수량")
        private Integer itemInvQty;
    }

}