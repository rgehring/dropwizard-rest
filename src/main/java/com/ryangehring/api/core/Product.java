package com.ryangehring.api.core;



import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by ryan on 8/20/16.
 */


public class Product {

    @NotNull
    @JsonProperty
    private Integer id;

    @NotNull
    @JsonProperty
    private String content;

    public Product() {
        // Jackson deserialization
    }

    public Product(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product that = (Product) o;

        if (!getId().equals(that.getId())) return false;

        return true;
    }
}
