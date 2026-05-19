package com.ecommerceJava.ecommerce.dto;

import java.time.Instant;

import com.ecommerceJava.ecommerce.entities.Payment;

public class PaymentDTO {
    private long id;
    private Instant moment;

    public PaymentDTO(long id, Instant moment) {
        this.id = id;
        this.moment = moment;
    }

    public PaymentDTO(Payment entity) {
        id = entity.getId();
        moment = entity.getMoment();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    
}
