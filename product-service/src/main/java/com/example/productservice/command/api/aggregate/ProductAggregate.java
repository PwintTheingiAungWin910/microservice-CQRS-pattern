package com.example.productservice.command.api.aggregate;

import com.example.productservice.command.api.commands.CreateProductCommand;
import com.example.productservice.command.api.events.ProductEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {
    @AggregateIdentifier
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    public ProductAggregate() {
    }

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        ProductEvent productEvent = new ProductEvent();
        BeanUtils.copyProperties(createProductCommand,productEvent);
        AggregateLifecycle.apply(productEvent); // publish the particular object
    }

    @EventSourcingHandler
    public void on(ProductEvent productEvent) {
        this.productId = productEvent.getProductId();
        this.name = productEvent.getName();
        this.price = productEvent.getPrice();
        this.quantity = productEvent.getQuantity();
    }
}
