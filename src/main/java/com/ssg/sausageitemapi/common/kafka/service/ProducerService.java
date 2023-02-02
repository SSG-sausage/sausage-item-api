package com.ssg.sausageitemapi.common.kafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssg.sausageitemapi.common.exception.ErrorCode;
import com.ssg.sausageitemapi.common.exception.InternalServerException;
import com.ssg.sausageitemapi.common.kafka.constant.KafkaConstants;
import com.ssg.sausageitemapi.common.kafka.dto.CartShareOrdItemDeleteListDto;
import com.ssg.sausageitemapi.common.kafka.dto.CartShareOrdItemInfo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final ObjectMapper objectMapper;

    public void deleteCartShareOrdItemList(List<CartShareOrdItemInfo> cartShareOrdItemInfoList, Long cartShareOrdId) {

        produceKafkaMsg(KafkaConstants.KAFKA_CART_SHARE_ORD_ITEM_DELETE,
                CartShareOrdItemDeleteListDto.of(cartShareOrdItemInfoList, cartShareOrdId));
    }

    private void produceKafkaMsg(String topicNm, Object object) {
        try {
            kafkaTemplate.send(topicNm, objectMapper.writeValueAsString(object));
        } catch (JsonProcessingException e) {
            throw new InternalServerException("예상치 못한 서버 에러가 발생하였습니다.", ErrorCode.INTERNAL_SERVER_EXCEPTION);
        }
    }
}
