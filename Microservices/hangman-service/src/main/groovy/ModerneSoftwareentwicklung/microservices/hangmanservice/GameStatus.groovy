package ModerneSoftwareentwicklung.microservices.hangmanservice

enum GameStatus {
    NOT_INITIALIZED ("Game is not initialized!"),
    RUNNING ("Game is on!"),
    GAME_OVER_YOU_LOST("Game over! You lost."),
    GAME_OVER_YOU_WON("Game over! You won!");

    def toStr

    GameStatus(toStr) {
        this.toStr = toStr
    }
}