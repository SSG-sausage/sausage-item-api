package com.ssg.sausageitemapi.item.dto.response;


import com.ssg.sausageitemapi.item.entity.Item;
import java.util.Collections;
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

    private List<ItemInfo> itemList = Collections.emptyList();

    public static ItemFindListResponse of(List<Item> itemList) {
        return ItemFindListResponse
                .builder()
                .itemList(itemList.stream().map(ItemInfo::of).collect(Collectors.toList()))
                .build();
    }
}
