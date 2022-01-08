package ModerneSoftwareentwicklung.microservices.hangmanservice

import groovy.util.logging.Log
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@Log
@RestController
class Controller {

    @PostMapping("/newgame")
    static ResponseEntity newGame() {
        log.info("--- New call to route: /newgame")

        def success = LazySingleton.instance.init()

        if (success) {
            return new ResponseEntity(HttpStatus.CREATED);
        } else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }

    }

    @PostMapping("/guess/{userGuess}")
    static DTOResponse guess(@PathVariable String userGuess) {
        log.info("--- New call to route: /guess/" + userGuess)

        LazySingleton.instance.guess(userGuess)

        DTOResponse responseDTO = new DTOResponse()
        responseDTO.word = LazySingleton.instance.getWord()
        responseDTO.gameStatus = LazySingleton.instance.getGameStatus()
        responseDTO.misses = LazySingleton.instance.getMisses()
        responseDTO.image = LazySingleton.instance.getImage ()

        return responseDTO;
    }
}
