package com.example.productservice.command.api.events;

import com.example.productservice.command.api.entity.Product;
import com.example.productservice.command.api.repository.ProductRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Component
@ProcessingGroup("product")
public class ProductEventHandler {
    @Autowired
    private ProductRepository productRepository;

    @EventHandler
    public void on(ProductEvent productEvent) throws Exception {
        Product product = new Product();
        BeanUtils.copyProperties(productEvent,product);
        productRepository.save(product);
        //throw new Exception("Exception Occurred");
    }

    @ExceptionHandler
    public void handle(Exception exception) throws Exception{
        throw exception;
    }
}
