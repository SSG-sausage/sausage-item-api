package com.ssg.sausageitemapi.item.service;

import com.ssg.sausageitemapi.item.dto.response.ItemFindListResponse;
import com.ssg.sausageitemapi.item.dto.response.ItemFindResponse;
import com.ssg.sausageitemapi.item.entity.Item;
import com.ssg.sausageitemapi.item.repository.ItemRepository;
import java.util.List;
import java.util.Optional;
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

    private final ItemServiceUtils itemServiceUtils;

    public ItemFindListResponse findAllItemList() {

        List<Item> itemList = itemRepository.findAll();

        return ItemFindListResponse.of(itemList);
    }

    public ItemFindResponse findItem(Long id) {

        Item item = itemServiceUtils.findItemById(id);

        return ItemFindResponse.of(item);

    }

    public ItemFindListResponse findItemList(List<Long> idList) {

        List<Item> itemList = itemRepository.findAllById(idList);

        return ItemFindListResponse.of(itemList);
    }
}
