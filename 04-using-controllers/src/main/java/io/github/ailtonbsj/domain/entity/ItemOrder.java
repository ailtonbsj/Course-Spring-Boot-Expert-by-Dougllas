package io.github.ailtonbsj.domain.entity;

import javax.persistence.*;

@Entity
public class ItemOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private PurchaseOrder orders;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product products;

    private Integer total;

    public ItemOrder() {
    }

    public ItemOrder(Integer id, PurchaseOrder orders, Product products, Integer total) {
        this.id = id;
        this.orders = orders;
        this.products = products;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PurchaseOrder getOrders() {
        return orders;
    }

    public void setOrders(PurchaseOrder orders) {
        this.orders = orders;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ItemOrder{" +
                "id=" + id +
                ", orders=" + orders +
                ", products=" + products +
                ", total=" + total +
                '}';
    }
}
