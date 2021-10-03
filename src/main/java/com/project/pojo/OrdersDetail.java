package com.project.pojo;

import javax.persistence.*;

@Entity
@Table(name = "Orders_detail", schema = "dbo", catalog = "ShoppingDB")
@IdClass(OrdersDetailPK.class)
public class OrdersDetail {
    private int orderId;
    private int productId;
    private Integer amountProduct;
    private Double priceProduct;

    @Id
    @Column(name = "order_id")
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Id
    @Column(name = "product_id")
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Basic
    @Column(name = "amount_product")
    public Integer getAmountProduct() {
        return amountProduct;
    }

    public void setAmountProduct(Integer amountProduct) {
        this.amountProduct = amountProduct;
    }

    @Basic
    @Column(name = "price_product")
    public Double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(Double priceProduct) {
        this.priceProduct = priceProduct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersDetail that = (OrdersDetail) o;

        if (orderId != that.orderId) return false;
        if (productId != that.productId) return false;
        if (amountProduct != null ? !amountProduct.equals(that.amountProduct) : that.amountProduct != null)
            return false;
        if (priceProduct != null ? !priceProduct.equals(that.priceProduct) : that.priceProduct != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + productId;
        result = 31 * result + (amountProduct != null ? amountProduct.hashCode() : 0);
        result = 31 * result + (priceProduct != null ? priceProduct.hashCode() : 0);
        return result;
    }
}
