package com.ssg.sausageitemapi.item.repository;

import com.ssg.sausageitemapi.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
