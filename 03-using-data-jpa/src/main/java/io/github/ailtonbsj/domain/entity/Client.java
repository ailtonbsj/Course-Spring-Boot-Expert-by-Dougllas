package io.github.ailtonbsj.domain.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 100)
    private String name;

    @OneToMany(mappedBy = "client")
    private Set<PurchaseOrder> purchaseOrders;

    public Client() {
    }

    public Client(Integer id, String name, Set<PurchaseOrder> purchaseOrders) {
        this.id = id;
        this.name = name;
        this.purchaseOrders = purchaseOrders;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PurchaseOrder> getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(Set<PurchaseOrder> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", purchaseOrders=" + purchaseOrders +
                '}';
    }
}
