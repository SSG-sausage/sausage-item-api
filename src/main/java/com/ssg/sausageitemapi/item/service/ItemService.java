package com.ssg.sausageitemapi.item.service;

import com.ssg.sausageitemapi.item.dto.request.ItemInvQtyValidateRequest;
import com.ssg.sausageitemapi.item.dto.request.ItemInvQtyValidateRequest.ItemInfo;
import com.ssg.sausageitemapi.item.dto.response.ItemFindListResponse;
import com.ssg.sausageitemapi.item.dto.response.ItemFindResponse;
import com.ssg.sausageitemapi.item.entity.Item;
import com.ssg.sausageitemapi.item.repository.ItemRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    private final ItemUtilService itemUtilService;

    public ItemFindListResponse findAllItemList() {

        List<Item> itemList = itemRepository.findAll();

        return ItemFindListResponse.of(itemList);
    }

    public ItemFindResponse findItem(Long id) {

        Item item = itemUtilService.findItemById(id);

        return ItemFindResponse.of(item);

    }

    public ItemFindListResponse findItemList(List<Long> idList) {

        List<Item> itemList = itemRepository.findAllById(idList);

        return ItemFindListResponse.of(itemList);
    }

    public boolean validateItemInvQty(ItemInvQtyValidateRequest itemInvQtyValidateRequest) {

        List<Long> itemIdList = itemInvQtyValidateRequest.getItemInfoList().stream()
                .map(ItemInfo::getItemId)
                .collect(Collectors.toList());

        List<Item> itemList = itemRepository.findAllById(itemIdList);

        Map<Long, Integer> itemInvQtyMap = itemList.stream()
                .collect(Collectors.toMap(Item::getItemId, Item::getItemInvQty));

        for (ItemInfo itemInfo : itemInvQtyValidateRequest.getItemInfoList()) {

            int itemInvQty = itemInvQtyMap.get(itemInfo.getItemId()) - itemInfo.getItemInvQty();

            if (itemInvQty < 0) {
                return false;
            }
        }

        return true;
    }
}
