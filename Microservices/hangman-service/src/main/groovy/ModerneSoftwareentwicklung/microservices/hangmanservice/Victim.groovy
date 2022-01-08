package ModerneSoftwareentwicklung.microservices.hangmanservice

class Victim {

    def head = false
    def body = false
    def armR = false
    def armL = false
    def legR = false
    def legL = false

    def image = [
            "  +---+ ",
            "  |   | ",
            "      | ",
            "      | ",
            "      | ",
            "      | ",
            "======+="
    ]


    boolean isDead () {
        return head && body && armR && armL && legR && legL
    }
}
