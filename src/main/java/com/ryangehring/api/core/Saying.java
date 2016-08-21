package com.ryangehring.api.core;



import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Created by ryan on 8/20/16.
 */


public class Saying {

    @NotNull
    @JsonProperty
    private Integer id;

    @NotNull
    @JsonProperty
    private String content;

    public Saying() {
        // Jackson deserialization
    }

    public Saying(int id, String content) {
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
        if (!(o instanceof Saying)) return false;

        Saying that = (Saying) o;

        if (!getId().equals(that.getId())) return false;

        return true;
    }
}
