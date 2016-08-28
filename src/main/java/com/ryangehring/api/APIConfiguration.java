package com.ryangehring.api;


import com.bendb.dropwizard.redis.JedisFactory;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.elasticsearch.config.EsConfiguration;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.UnsupportedEncodingException;


/**
 * Created by ryan on 8/20/16.
 */
public class APIConfiguration extends Configuration  {


    @NotEmpty
    private String jwtTokenSecret = "C6BDF52316E54012E0FCD4DF77CEC10DAC524DAB89CBC5DA3770E9E4A61589DE";

    public byte[] getJwtTokenSecret() throws UnsupportedEncodingException {
        return jwtTokenSecret.getBytes("UTF-8");
    }

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @NotNull
    @JsonProperty
    private JedisFactory redis;

    public JedisFactory getJedisFactory() {
        return redis;
    }

    public void setJedisFactory(JedisFactory jedisFactory) {
        this.redis = jedisFactory;
    }

    public EsConfiguration getEsConfiguration() {
        return esConfiguration;
    }

    public void setEsConfiguration(EsConfiguration esConfiguration) {
        this.esConfiguration = esConfiguration;
    }

    private EsConfiguration esConfiguration ;


}
