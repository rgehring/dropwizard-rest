package com.ryangehring.api.resources;


import com.ryangehring.api.core.Product;
import com.ryangehring.api.dao.ProductDAO;
import com.ryangehring.api.utils.ExperimentService;
import redis.clients.jedis.Jedis;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam ;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;


@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {


    ProductDAO productDAO;

    public ProductResource(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GET
    @Path("/{id}")
    public Product get(@PathParam("id") Integer id, @Context Jedis jedis){

        String cachedProduct = jedis.get("product-" + id);
        Product product = new Product(id, cachedProduct) ;
        return product;

    }


}