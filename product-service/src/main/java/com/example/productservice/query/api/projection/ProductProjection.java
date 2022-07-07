package com.example.productservice.query.api.projection;

import com.example.productservice.command.api.entity.Product;
import com.example.productservice.command.api.model.ProductRestModel;
import com.example.productservice.command.api.repository.ProductRepository;
import com.example.productservice.query.api.queries.GetProductQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {
    @Autowired
    private ProductRepository productRepository;

    @QueryHandler
    public List<ProductRestModel> getALlProducts(GetProductQuery productQuery){
        List<Product> products = productRepository.findAll();
        return  products.stream().map(p -> ProductRestModel.builder()
                        .name(p.getName())
                        .price(p.getPrice())
                        .quantity(p.getQuantity())
                        .build())
                        .collect(Collectors.toList());
    }
}
