package com.project.pojo;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Products {
    private int id;
    private String name;
    private String description;
    private double price;
    private String src;
    private String type;
    private String brand;
    private Boolean status;
    private Date importDate;
    public int number;
    public double importPrice;

    public Products() {
    }

    public Products(String name, String description, double price, String src, String type, String brand, double importPrice) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.src = src;
        this.type = type;
        this.brand = brand;
        this.importPrice = importPrice;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    public int getId() {
        return id;
    }

    public void setId(int productId) {
        this.id = productId;
    }

    @Basic
    @Column(name = "product_number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Basic
    @Column(name = "import_price")
    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    @Basic
    @Column(name = "product_name")
    public String getName() {
        return name;
    }

    public void setName(String productName) {
        this.name = productName;
    }

    @Basic
    @Column(name = "product_des")
    public String getDescription() {
        return description;
    }

    public void setDescription(String productDes) {
        this.description = productDes;
    }

    @Basic
    @Column(name = "product_price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double productPrice) {
        this.price = productPrice;
    }

    @Basic
    @Column(name = "product_img_source")
    public String getSrc() {
        return src;
    }

    public void setSrc(String productImgSource) {
        this.src = productImgSource;
    }

    @Basic
    @Column(name = "product_type")
    public String getType() {
        return type;
    }

    public void setType(String productType) {
        this.type = productType;
    }

    @Basic
    @Column(name = "product_brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String productBrand) {
        this.brand = productBrand;
    }

    @Basic
    @Column(name = "product_status")
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean productStatus) {
        this.status = productStatus;
    }

    @Basic
    @Column(name = "product_import_date")
    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date productImportDate) {
        this.importDate = productImportDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Products products = (Products) o;

        if (id != products.id) return false;
        if (Double.compare(products.price, price) != 0) return false;
        if (name != null ? !name.equals(products.name) : products.name != null)
            return false;
        if (description != null ? !description.equals(products.description) : products.description != null) return false;
        if (src != null ? !src.equals(products.src) : products.src != null)
            return false;
        if (type != null ? !type.equals(products.type) : products.type != null)
            return false;
        if (brand != null ? !brand.equals(products.brand) : products.brand != null)
            return false;
        if (status != null ? !status.equals(products.status) : products.status != null)
            return false;
        if (importDate != null ? !importDate.equals(products.importDate) : products.importDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (src != null ? src.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (importDate != null ? importDate.hashCode() : 0);
        return result;
    }
}
