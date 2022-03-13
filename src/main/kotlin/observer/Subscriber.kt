package observer

class Subscriber(private val name: String) {
    fun display(message: String) {
        println("$name: $message")
    }
}
