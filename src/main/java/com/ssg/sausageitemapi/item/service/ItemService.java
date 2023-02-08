package com.ssg.sausageitemapi.item.service;

import com.ssg.sausageitemapi.common.exception.ErrorCode;
import com.ssg.sausageitemapi.common.exception.ValidationException;
import com.ssg.sausageitemapi.item.dto.request.ItemInvQtyUpdateInfo;
import com.ssg.sausageitemapi.item.dto.request.ItemInvQtyUpdateListRequest;
import com.ssg.sausageitemapi.item.dto.request.ItemInvQtyValidateRequest;
import com.ssg.sausageitemapi.item.dto.request.ItemInvQtyValidateRequest.ItemValidateInfo;
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

    @Transactional
    public void updateItemInvQty(ItemInvQtyUpdateListRequest request) {

        for (ItemInvQtyUpdateInfo itemInvQtyUpdateInfo : request.getItemInvQtyUpdateInfoList()) {

            Item item = itemUtilService.findItemById(itemInvQtyUpdateInfo.getItemId());

            if (!item.canDecreaseItemInvQty(itemInvQtyUpdateInfo.getItemInvQty())) {
                throw new ValidationException("주문 상품 중 재고 소진된 상품이 존재합니다.", ErrorCode.VALIDATION_ITEM_INV_QTY);
            }
            
            item.changeItemInvQty(itemInvQtyUpdateInfo.getItemInvQty(), request.getUpdateType().getValue());
        }

    }

    public ItemFindListResponse findItemList(List<Long> idList) {

        List<Item> itemList = itemRepository.findAllById(idList);

        return ItemFindListResponse.of(itemList);
    }

    public boolean validateItemInvQty(ItemInvQtyValidateRequest request) {

        List<Long> itemIdList = request.getItemValidateInfoList().stream()
                .map(ItemValidateInfo::getItemId)
                .collect(Collectors.toList());

        List<Item> itemList = itemRepository.findAllById(itemIdList);

        Map<Long, Integer> itemInvQtyMap = itemList.stream()
                .collect(Collectors.toMap(Item::getItemId, Item::getItemInvQty));

        for (ItemValidateInfo itemValidateInfo : request.getItemValidateInfoList()) {

            int itemInvQty = itemInvQtyMap.get(itemValidateInfo.getItemId()) - itemValidateInfo.getItemInvQty();

            if (itemInvQty < 0) {
                return false;
            }
        }
        return true;
    }
}
