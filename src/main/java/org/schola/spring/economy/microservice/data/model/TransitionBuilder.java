package org.schola.spring.economy.microservice.data.model;

import org.schola.spring.economy.microservice.router.controller.NewTransitionRequest;

import java.util.UUID;

public class TransitionBuilder {

    private String id;
    private double amount;
    private TransitionType type;
    private Purse purse;

    public TransitionBuilder(String id) {
        this.id = id;
    }

    public TransitionBuilder(String id, double amount, TransitionType type) {
        this.id = id;
        this.amount = amount;
        this.type = type;
    }

    public static TransitionBuilder fromNewTransitionRequest(NewTransitionRequest request) {
        final String id = UUID.randomUUID().toString();
        final double amount = request.getAmount();
        final TransitionType type = request.getType();
        return new TransitionBuilder(id, amount, type);
    }

    public TransitionBuilder id(String id) {
        this.id = id;
        return this;
    }

    public TransitionBuilder amount(double amount) {
        this.amount = amount;
        return this;
    }

    public TransitionBuilder type(TransitionType type) {
        this.type = type;
        return this;
    }

    public TransitionBuilder purse(Purse purse) {
        this.purse = purse;
        return this;
    }

    public Transition build() {
        return new Transition(id, purse, type, amount);
    }
}
