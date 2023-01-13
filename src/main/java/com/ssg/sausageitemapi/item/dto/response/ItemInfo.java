package com.ssg.sausageitemapi.item.dto.response;

import com.ssg.sausageitemapi.item.entity.Item;
import com.ssg.sausageitemapi.item.entity.ShppCd;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class ItemInfo {

    @Schema(description = "상품 ID")
    private Long id;

    @Schema(description = "상품 이름")
    private String itemNm;

    @Schema(description = "상품 가격")
    private Integer itemAmt;

    @Schema(description = "상품 수량")
    private String itemInvQty;

    @Schema(description = "멤버 ID")
    private String itemImgUrl;

    @Schema(description = "멤버 ID")
    private String shppCd;

    public static ItemInfo of(Item item) {
        return ItemInfo
                .builder()
                .id(item.getId())
                .itemImgUrl(item.getItemImgUrl())
                .itemNm(item.getItemNm())
                .itemInvQty(item.getItemInvQty())
                .itemAmt(item.getItemAmt())
                .shppCd(item.getShppCd().getNm())
                .build();
    }
}
