package com.farm2pot.product.entity;

import com.farm2pot.common.exception.BusinessErrorCode;
import com.farm2pot.common.exception.DomainException;
import jakarta.persistence.*;
import lombok.*;

import java.util.concurrent.atomic.AtomicInteger;

@Embeddable
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    private AtomicInteger quantity;

    public void increase(AtomicInteger amount) {
        this.quantity.addAndGet(amount.get());
    }

    public void decrease(AtomicInteger amount) {
        int currentQty = this.quantity.get();
        if (currentQty < amount.get()) {
            throw new DomainException(BusinessErrorCode.OUT_OF_STOCK);
        }
        this.quantity.addAndGet(-amount.get());
    }
}
