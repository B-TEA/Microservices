package ModerneSoftwareentwicklung.microservices.hangservice

import groovy.util.logging.Log
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Log
@RestController
class Controller {

    private DTOVictim hang (DTOVictim victim) {

        if (!victim.head) {
            log.info("Hanging head.")
            victim.head = true
            victim.image = Images.img1

        } else if (!victim.body) {
            log.info("Hanging body.")
            victim.body = true
            victim.image = Images.img2

        } else if (!victim.armL) {
            log.info("Hanging left arm.")
            victim.armL = true
            victim.image = Images.img3

        } else if (!victim.armR) {
            log.info("Hanging right arm.")
            victim.armR = true
            victim.image = Images.img4

        } else if (!victim.legL) {
            log.info("Hanging left leg.")
            victim.legL = true
            victim.image = Images.img5

        } else if (!victim.legR) {
            log.info("Hanging right leg.")
            victim.legR = true
            victim.image = Images.img6

        } else {
            log.info("Nothing to hang.")
            victim.image = Images.img6
        }

        return  victim
    }
     

    @PostMapping("/punish")
    public DTOVictim punish (@RequestBody DTOVictim dtoVictim) {

        log.info("--- New call to route: /punish")
        DTOVictim result = hang(dtoVictim)
        log.info("Hanging complete.")

        return result
    }

}
