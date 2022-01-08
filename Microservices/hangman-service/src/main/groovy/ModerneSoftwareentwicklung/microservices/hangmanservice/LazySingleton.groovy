package ModerneSoftwareentwicklung.microservices.hangmanservice

import groovy.util.logging.Log
import org.springframework.web.client.RestTemplate

@Log
@Singleton
class LazySingleton {

    // Sorry, hardcoded.
    static final String DICTIONARY_SERVICE_URI_GET_WORD = "http://172.17.0.2:9000/getword";
    static final String HANG_SERVICE_URI_PUNISH = "http://172.17.0.3:9000/punish";

    def victim = new Victim()
    def dictionaryWord
    def word
    def misses = [] as Set
    def gameStatus = GameStatus.NOT_INITIALIZED


    boolean init () {
        def success = true

        log.info("Initializing game")

        log.info ("Spawning new body")
        victim = new Victim()

        log.info("Requesting word from dictionary-service ...")
        dictionaryWord = getWordFromDictionary().toLowerCase() << ''
        log.info("... dictionary-service replied with: "+ dictionaryWord)

        word = dictionaryWord.replaceAll("\\S","_") << ''

        misses = [] as Set

        if (dictionaryWord.length() == 0) {
            gameStatus = GameStatus.NOT_INITIALIZED
        } else {
            gameStatus = GameStatus.RUNNING
        }

        return success
    }

    void guess(String c) {

        if (gameStatus = GameStatus.RUNNING) {
            if (dictionaryWord.indexOf(c) != -1) {

                log.info("guess (" + c + ") IS IN word (" + dictionaryWord + ")")

                for (int i in 0..<dictionaryWord.size()) {
                    if (dictionaryWord.charAt(i) == c) {
                        word.replace(i, i + 1, c)
                    }
                }

                if (dictionaryWord.toString().equals(word.toString())) {
                    log.info("User guessed the word correctly.")
                    gameStatus = GameStatus.GAME_OVER_YOU_WON
                }

            } else {
                log.info("guess (" + c + ") IS NOT IN word (" + dictionaryWord + ")")
                log.info("Send victim to hang-service ...")
                this.victim = sendVictimToHangService(this.victim)
                log.info("... parts of victim got hanged.")

                misses << c

                if (victim.isDead()) {
                    log.info("Complete victim has been hung up.")
                    gameStatus = GameStatus.GAME_OVER_YOU_LOST
                }

            }
        }
    }

    String[] getImage() {
        return victim.image
    }

    private static String getWordFromDictionary() {
        RestTemplate restTemplate = new RestTemplate();
        DictionaryWord dictResponse = restTemplate.getForObject(DICTIONARY_SERVICE_URI_GET_WORD, DictionaryWord.class);
        return dictResponse.word
    }

    private static Victim sendVictimToHangService(Victim victim) {
        RestTemplate restTemplate = new RestTemplate();
        Victim hangResponse = restTemplate.postForObject(HANG_SERVICE_URI_PUNISH, victim, Victim.class);
        return hangResponse
    }
}
