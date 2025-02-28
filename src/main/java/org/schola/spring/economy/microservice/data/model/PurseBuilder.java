package org.schola.spring.economy.microservice.data.model;

public class PurseBuilder {

    private String identifier;
    private double amount;

    public PurseBuilder(String identifier, double amount) {
        this.identifier = identifier;
        this.amount = amount;
    }

    public static PurseBuilder newDefault(String identifier) {
        return new PurseBuilder(identifier, 0);
    }

    public PurseBuilder amount(double amount) {
        this.amount = amount;
        return this;
    }

    public PurseBuilder identifier(String identifier) {
        this.identifier = identifier;
        return this;
    }

    public Purse build() {
        return new Purse(this.identifier, this.amount);
    }
}
