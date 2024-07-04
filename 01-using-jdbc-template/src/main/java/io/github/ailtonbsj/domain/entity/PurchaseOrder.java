package io.github.ailtonbsj.domain.entity;

import java.math.BigInteger;
import java.util.Date;

public class PurchaseOrder {
    Integer id;
    Client client;
    Date orderDate;
    BigInteger amount;
}
