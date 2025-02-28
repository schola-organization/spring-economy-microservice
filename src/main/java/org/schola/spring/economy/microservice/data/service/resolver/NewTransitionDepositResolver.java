package org.schola.spring.economy.microservice.data.service.resolver;

import org.schola.spring.economy.microservice.data.model.Purse;
import org.schola.spring.economy.microservice.data.model.Transition;
import org.schola.spring.economy.microservice.data.service.PurseService;
import org.schola.spring.economy.microservice.data.service.TransitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NewTransitionDepositResolver {

    @Autowired
    private PurseService purseService;

    @Autowired
    private TransitionService transitionService;

    public void deposit(Transition transition) {
        final Purse purse = transition.getPurse();
        this.transitionService.save(transition);
        final double result = purse.getAmount() + transition.getAmount();
        purse.setAmount(result);
        this.purseService.save(purse);
    }
}
