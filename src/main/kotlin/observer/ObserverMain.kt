package observer

fun startObserver() {
    val countDown = CountDown()
    countDown.start()

    val ilia = Subscriber("Ilia")
    val dasha = Subscriber("Dasha")

    countDown.subscribe(ilia)
    countDown.subscribe(dasha)

    println("Press button")
    readLine()

    println("Start count down")
    countDown.notifyAll(1000, "3 seconds left")
    countDown.notifyAll(1000, "2 seconds left")
    countDown.notifyAll(1000, "1 seconds left")
    countDown.notifyAll(1000, "End")

    Thread.sleep(5000)
    countDown.close()
}