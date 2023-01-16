package com.ssg.sausageitemapi.item.dto.response;

import com.ssg.sausageitemapi.item.entity.Item;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Builder(access = AccessLevel.PRIVATE)
public class ItemFindResponse {

    @Schema(description = "상품 ID")
    private Long itemId;

    @Schema(description = "상품 이름")
    private String itemNm;

    @Schema(description = "상품 브랜드 이름")
    private String itemBrandNm;

    @Schema(description = "상품 가격")
    private Integer itemAmt;

    @Schema(description = "상품 수량")
    private String itemInvQty;

    @Schema(description = "멤버 ID")
    private String itemImgUrl;

    @Schema(description = "멤버 ID")
    private String shppCd;

    public static ItemFindResponse of(Item item) {
        return ItemFindResponse.builder()
                .itemId(item.getItemId())
                .itemNm(item.getItemNm())
                .itemBrandNm(item.getItemBrandNm())
                .itemAmt(item.getItemAmt())
                .itemImgUrl(item.getItemImgUrl())
                .itemInvQty(item.getItemInvQty())
                .shppCd(item.getShppCd().getNm())
                .build();
    }
}
