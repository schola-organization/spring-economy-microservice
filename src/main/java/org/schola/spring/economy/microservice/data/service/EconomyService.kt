package org.schola.spring.economy.microservice.data.service

import org.schola.spring.economy.microservice.data.model.Purse
import org.schola.spring.economy.microservice.data.model.Transition
import org.schola.spring.economy.microservice.data.model.TransitionType
import org.schola.spring.economy.microservice.data.service.resolver.NewTransitionDepositResolver
import org.schola.spring.economy.microservice.data.service.resolver.NewTransitionWithdrawResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class EconomyService(
    private var transitionService: TransitionService,
    private var purseService: PurseService,
    private var newTransitionDepositResolver: NewTransitionDepositResolver,
    private var newTransitionWithdrawResolver: NewTransitionWithdrawResolver
) {

    fun savePurse(purse: Purse) {
        purseService.save(purse)
    }

    fun saveTransition(transition: Transition) {
        if (transition.type == TransitionType.DEPOSIT)
            newTransitionDepositResolver.deposit(transition)
        else
            newTransitionWithdrawResolver.withdraw(transition)
    }

    fun getPurse(identifier: String): Purse = purseService.get(identifier)

    fun getPurses(pageable: Pageable): List<Purse> = purseService.getAll(pageable)

    fun getTransition(identifier: String): Transition = transitionService.get(identifier)

    fun getTransitions(pageable: Pageable): List<Transition> = transitionService.getAll(pageable)

    fun getTransitionsByPurseIdentifier(identifier: String): List<Transition> {
        val purse = purseService.get(identifier)
        return transitionService.getWithPurse(purse)
    }

    fun getTransitionsByPurse(purse: Purse): List<Transition> = transitionService.getWithPurse(purse)

}
