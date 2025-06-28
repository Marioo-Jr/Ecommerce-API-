package com.ecommerceJava.ecommerce.entities;

import jakarta.persistence.*;

import java.time.Instant;


@Entity
@Table(name = "tb_payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE") // define a time zone como UTC
    private Instant moment;

    @OneToOne
    @MapsId //mapeia ID >> vai ter o mesmo numero do pedido correspondente
    private Order order;

    public Payment() {

    }

    public Payment(long id, Order order, Instant moment) {
        this.id = id;
        this.order = order;
        this.moment = moment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;
        return id == payment.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}
