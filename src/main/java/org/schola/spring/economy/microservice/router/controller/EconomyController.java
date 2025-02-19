package org.schola.spring.economy.microservice.router.controller;

import org.schola.spring.economy.microservice.data.model.Purse;
import org.schola.spring.economy.microservice.data.model.Transition;
import org.schola.spring.economy.microservice.data.service.PurseService;
import org.schola.spring.economy.microservice.data.service.TransitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/economy")
public class EconomyController {

    @Autowired
    private PurseService purse;

    @Autowired
    private TransitionService transition;

    @PostMapping("/newPurse")
    public ResponseEntity<Purse> newPurse(@RequestBody String identifier) {
        final Purse purse = new Purse(identifier, 0.0);
        return ResponseEntity.ok(purse);
    }

    @PostMapping
    public ResponseEntity<Transition> newTransition(@RequestBody Transition transition) {

    }
}
