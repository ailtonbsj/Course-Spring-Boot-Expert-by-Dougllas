package io.github.ailtonbsj.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@Entity
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private LocalDate orderDate;

    @Column(precision = 20, scale = 2)
    private BigDecimal amount;

    @OneToMany(mappedBy = "orders")
    private List<ItemOrder> items;

    public PurchaseOrder() {
    }

    public PurchaseOrder(Integer id, Client client, LocalDate orderDate, BigDecimal amount, List<ItemOrder> items) {
        this.id = id;
        this.client = client;
        this.orderDate = orderDate;
        this.amount = amount;
        this.items = items;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<ItemOrder> getItems() {
        return items;
    }

    public void setItems(List<ItemOrder> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", amount=" + amount +
                '}';
    }
}
