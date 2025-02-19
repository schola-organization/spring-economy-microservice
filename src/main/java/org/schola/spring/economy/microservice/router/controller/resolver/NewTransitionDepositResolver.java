package org.schola.spring.economy.microservice.router.controller.resolver;

import org.schola.spring.economy.microservice.data.model.Purse;
import org.schola.spring.economy.microservice.data.model.Transition;
import org.schola.spring.economy.microservice.data.service.PurseService;
import org.schola.spring.economy.microservice.data.service.TransitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewTransitionDepositResolver {

    @Autowired
    private PurseService purse;

    @Autowired
    private TransitionService transition;

    public void deposit(String identifier, Transition transition) {
        final Purse purse = transition.getPurse();
        this.transition.save(transition);
        final double amount = transition.getAmount();
        final double result = purse.getAmount() + transition.getAmount();
        purse.setAmount(result);
        this.purse.save(purse);
    }
}
