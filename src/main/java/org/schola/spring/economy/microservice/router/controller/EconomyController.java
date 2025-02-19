package org.schola.spring.economy.microservice.router.controller;

import org.schola.spring.economy.microservice.data.model.Purse;
import org.schola.spring.economy.microservice.data.model.Transition;
import org.schola.spring.economy.microservice.data.model.TransitionBuilder;
import org.schola.spring.economy.microservice.data.model.TransitionType;
import org.schola.spring.economy.microservice.data.service.PurseService;
import org.schola.spring.economy.microservice.data.service.TransitionService;
import org.schola.spring.economy.microservice.router.controller.resolver.NewTransitionDepositResolver;
import org.schola.spring.economy.microservice.router.controller.resolver.NewTransitionWithdrawResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/api/economy")
public class EconomyController {

    @Autowired
    private PurseService purse;

    @Autowired
    private TransitionService transition;

    @Autowired
    private NewTransitionWithdrawResolver withdrawResolver;

    @Autowired
    private NewTransitionDepositResolver depositResolver;

    @PostMapping("/newPurse")
    public ResponseEntity<Purse> newPurse(@RequestBody String identifier) {
        final Purse purse = new Purse(identifier, 0.0);
        return ResponseEntity.ok(purse);
    }

    @PostMapping
    public ResponseEntity<Transition> newTransition(@RequestBody NewTransitionRequest request) {

        final String target = request.getTarget();
        final TransitionType type = request.getType();

        final Purse purse = this.purse.get(target);
        final Transition transition = TransitionBuilder.fromNewTransitionRequest(request).purse(purse).build();

        if (type == TransitionType.DEPOSIT) {
            this.depositResolver.deposit(target, transition);
        } else {
            this.withdrawResolver.withdraw(transition);
        }

        return ResponseEntity.ok(transition);
    }
}
