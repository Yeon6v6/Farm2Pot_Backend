package com.farm2pot.product.entity;

import jakarta.persistence.*;
import lombok.*;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
    private Integer quantity;

    public void increase(Integer amount) {
        this.quantity += amount;
    }

    public void decrease(Integer amount) {
        if (this.quantity < amount) {
            throw new IllegalStateException("재고가 부족합니다.");
        }
        this.quantity -= amount;
    }
}
