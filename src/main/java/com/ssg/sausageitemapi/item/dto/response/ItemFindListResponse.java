package com.ssg.sausageitemapi.item.dto.response;


import com.ssg.sausageitemapi.item.entity.Item;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
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
public class ItemFindListResponse {

    @Schema(description = "상품 ID 맵")
    private HashMap<Long, ItemInfo> itemMap;

    public static ItemFindListResponse of(List<Item> itemList) {

        return ItemFindListResponse
                .builder()
                .itemMap((HashMap<Long, ItemInfo>) itemList.stream()
                        .collect(Collectors.toMap(Item::getItemId, ItemInfo::of)))
                .build();
    }
}
