package org.schola.spring.economy.microservice.router.controller;

import org.schola.spring.economy.microservice.data.model.*;
import org.schola.spring.economy.microservice.data.service.EconomyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/economy")
public class EconomyController {

    private final EconomyService service;

    public EconomyController(EconomyService service) {
        this.service = service;
    }

    @PostMapping("/purse")
    public ResponseEntity<Purse> newPurse(@RequestBody String identifier) {
        Purse purse = PurseBuilder.newDefault(identifier).build();
        service.savePurse(purse);
        return ResponseEntity.ok(purse);
    }

    @PostMapping("/transition")
    public ResponseEntity<Transition> newTransition(@RequestBody NewTransitionRequest request) {
        Purse purse = service.getPurse(request.getTarget());

        Transition transition = TransitionBuilder.fromNewTransitionRequest(request)
                .purse(purse)
                .build();

        service.saveTransition(transition);
        return ResponseEntity.ok(transition);
    }

    @GetMapping("/purse/{identifier}")
    public ResponseEntity<Purse> getPurse(@PathVariable String identifier) {
        Purse purse = service.getPurse(identifier);
        return ResponseEntity.ok(purse);
    }

    @GetMapping("/transition/{identifier}")
    public ResponseEntity<Transition> getTransition(@PathVariable String identifier) {
        Transition transition = service.getTransition(identifier);
        return ResponseEntity.ok(transition);
    }

    @GetMapping("/transition/purse/{identifier}")
    public ResponseEntity<List<Transition>> getTransitionsByPurse(@PathVariable String identifier) {
        List<Transition> transitions = service.getTransitionsByPurseIdentifier(identifier);
        return ResponseEntity.ok(transitions);
    }
}
