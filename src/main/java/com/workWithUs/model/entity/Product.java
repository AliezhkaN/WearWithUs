package com.workWithUs.model.entity;

import java.util.Objects;

public class Product {

    private int id;
    private String src;
    private String name;
    private Gender gender;
    private Type type;
    private int price;


    public static class Builder {

        private final Product product;

        public Builder() {
            product = new Product();
        }

        public Product.Builder withId(int id) {
            product.id = id;
            return this;
        }

        public Product.Builder withSrc(String src) {
            product.src = src;
            return this;
        }

        public Product.Builder withName(String name) {
            product.name = name;
            return this;
        }

        public Product.Builder withGender(String gender) {
            product.gender = Gender.valueOf(gender);
            return this;
        }

        public Product.Builder withType(String type) {
            product.type = Type.valueOf(type);
            return this;
        }

        public Product.Builder withPrice(int price) {
            product.price = price;
            return this;
        }

        public Product build() {
            return product;
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id && price == product.price && Objects.equals(src, product.src) && Objects.equals(name, product.name) && gender == product.gender && type == product.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, src, name, gender, type, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", src='" + src + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", type=" + type +
                ", price=" + price +
                '}';
    }
}
