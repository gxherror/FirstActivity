package top.xherror.first_activity

inline fun n1AndN2(func:(Int,Int)->Int):Int{
    return func(3,5)
}

fun StringBuilder.build(func: StringBuilder.()->Unit):StringBuilder{
    func()
    return this
}

fun printString(str: String, block: (String) -> Unit) {
    block(str)
}

inline fun printStringInline(str: String, block: (String) -> Unit) {
    block(str)

}

fun main(){

    n1AndN2 { n1, n2 ->
        n1+n2
    }

    val result=StringBuilder().build {
        append("Start eat!")
    }

    val str = ""
    printString(str) { s ->
        if (s.isEmpty()) return@printString
        println(s)
    }

    printStringInline(str) { s ->
        if (s.isEmpty()) return
        println(s)
    }

}

