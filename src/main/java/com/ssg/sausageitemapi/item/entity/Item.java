package com.ssg.sausageitemapi.item.entity;


import com.ssg.sausageitemapi.common.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Table(name = "ITEM")
@Getter
@Entity
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long itemId;

    @Column(name = "ITEM_BRAND_NM")
    private String itemBrandNm;

    @Column(name = "ITEM_NM")
    private String itemNm;

    @Column(name = "ITEM_AMT")
    private Integer itemAmt;

    @Column(name = "ITEM_INV_QTY")
    private Long itemInvQty;

    @Column(name = "ITEM_IMG_URL")
    private String itemImgUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "SHPP_CD")
    private ShppCd shppCd;

    public boolean canDecreaseItemInvQty(int itemInvQty) {
        return this.itemInvQty - itemInvQty >= 0;
    }

    public void changeItemInvQty(int itemInvQty) {
        this.itemInvQty -= itemInvQty;
    }


}