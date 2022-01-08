package ModerneSoftwareentwicklung.microservices.dictionaryservice

import groovy.util.logging.Log
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Log
@RestController
class Controller {

    def dictionary = [
            "acceptance",
            "foundation",
            "straighten",
            "enthusiasm",
            "psychology",
            "remunerate",
            "conscience",
            "atmosphere",
            "particular",
            "understand",
            "experiment",
            "obligation",
            "dictionary",
            "provincial",
            "motivation"
    ]

    @GetMapping("/getword")
    public DTOWord getWord() {
        log.info("--- New call to route: /getword")
        Collections.shuffle dictionary
        DTOWord wordDTO = new DTOWord(dictionary[0])
        log.info("Responding with word: " + wordDTO.word)

        return wordDTO
    }
}
