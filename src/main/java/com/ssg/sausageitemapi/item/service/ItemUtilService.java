package com.ssg.sausageitemapi.item.service;

import com.ssg.sausageitemapi.common.exception.ErrorCode;
import com.ssg.sausageitemapi.common.exception.NotFoundException;
import com.ssg.sausageitemapi.item.entity.Item;
import com.ssg.sausageitemapi.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemUtilService {

    private final ItemRepository itemRepository;

    public Item findItemById(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(() -> {
            throw new NotFoundException(String.format("존재하지 않는 아이템 ID (%s) 입니다", itemId),
                    ErrorCode.NOT_FOUND_PRODUCT_EXCEPTION);
        });
    }

}
