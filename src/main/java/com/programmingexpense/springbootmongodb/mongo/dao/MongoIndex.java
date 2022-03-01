package com.programmingexpense.springbootmongodb.mongo.dao;

import com.programmingexpense.springbootmongodb.mongo.enums.Order;

public class MongoIndex {

    private String field;
    private Order order;
    private boolean unique;

    public String getField() {
        return field;
    }

    public MongoIndex setField(String field) {
        this.field = field;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public MongoIndex setOrder(Order order) {
        this.order = order;
        return this;
    }

    public boolean isUnique() {
        return unique;
    }

    public MongoIndex setUnique(boolean unique) {
        this.unique = unique;
        return this;
    }

    @Override
    public String toString() {
        return "MongoIndex{" +
                "field='" + field + '\'' +
                ", order=" + order +
                ", unique=" + unique +
                '}';
    }
}
