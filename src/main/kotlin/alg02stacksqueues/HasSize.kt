package alg02stacksqueues

interface HasSize {
    val size: Int
    val isEmpty: Boolean
        get() = this.size == 0
}
