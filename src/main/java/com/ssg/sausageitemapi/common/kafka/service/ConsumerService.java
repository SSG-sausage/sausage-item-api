package com.ssg.sausageitemapi.common.kafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssg.sausageitemapi.common.exception.ErrorCode;
import com.ssg.sausageitemapi.common.exception.InternalServerException;
import com.ssg.sausageitemapi.common.kafka.constant.KafkaConstants;
import com.ssg.sausageitemapi.common.kafka.dto.ItemInvQtyUpdateListDto;
import com.ssg.sausageitemapi.item.dto.request.ItemInvQtyUpdateInfo;
import com.ssg.sausageitemapi.item.entity.Item;
import com.ssg.sausageitemapi.item.service.ItemUtilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ConsumerService {

    private final ObjectMapper objectMapper;

    private final ItemUtilService itemUtilService;

    @KafkaListener(topics = KafkaConstants.KAFKA_ITEM_INV_QTY_UPDATE, groupId = KafkaConstants.CONSUMER_GROUP_ID)
    public void updateItemInvQty(String consumeMsg) {

        try {

            ItemInvQtyUpdateListDto itemInvQtyUpdateListDto = objectMapper.readValue(consumeMsg,
                    ItemInvQtyUpdateListDto.class);

            int updateTypeValue = itemInvQtyUpdateListDto.getItemInvQtyUpdateListDtoType().getValue();

            for (ItemInvQtyUpdateInfo itemInvQtyUpdateInfo : itemInvQtyUpdateListDto.getItemInvQtyUpdateInfoList()) {

                Item item = itemUtilService.findItemById(itemInvQtyUpdateInfo.getItemId());

                item.changeItemInvQty(itemInvQtyUpdateInfo.getItemInvQty(), updateTypeValue);
            }

        } catch (JsonProcessingException e) {
            throw new InternalServerException("예상치 못한 서버 에러가 발생하였습니다.", ErrorCode.INTERNAL_SERVER_EXCEPTION);
        }
    }
}
