package alg05symboltables

class IllegalKeyException(val key: Any?) : Exception() {
    override val message: String?
        get() = "key $key is not present in the table"
}
