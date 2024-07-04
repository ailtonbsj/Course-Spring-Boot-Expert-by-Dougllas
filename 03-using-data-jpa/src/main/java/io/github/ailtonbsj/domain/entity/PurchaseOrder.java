package io.github.ailtonbsj.domain.entity;

import javax.persistence.*;
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

    @Column(length = 20, precision = 2)
    private BigInteger amount;

    @OneToMany(mappedBy = "orders")
    private List<ItemOrder> items;

    public PurchaseOrder() {
    }

    public PurchaseOrder(Integer id, Client client, LocalDate orderDate, BigInteger amount, List<ItemOrder> items) {
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

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
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
                ", client=" + client +
                ", orderDate=" + orderDate +
                ", amount=" + amount +
                ", items=" + items +
                '}';
    }
}
