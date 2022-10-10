package top.xherror.first_activity

import com.google.android.material.chip.ChipDrawable.Delegate
import kotlin.reflect.KProperty

inline fun n1AndN2(func:(Int,Int)->Int):Int{
    return func(3,5)
}

fun StringBuilder.build(func: StringBuilder.()->Unit):StringBuilder{
    func()
    return this
}

class MyClass1<T:Any>{
    fun method(param1:T):T {
        return param1
    }
}

class MyClass2{
    fun <T:Number> method(param1:T){

    }
    var p by later {
        println("later")
        233
    }

}


fun <T> later(block: () -> T) = Later(block)

class Later<T>(val block: () -> T){
    var value:Any?=null
    operator fun getValue(myClass2: MyClass2,prop:KProperty<*>):T{
        if (value==null){
            value=block()
        }
        return value as T
    }

    operator fun setValue(myClass2: MyClass2,prop:KProperty<*>,value: Any?){
        //propValue=value
    }
}

class Delegate{
    var propValue:Any?=null

    operator fun getValue(myClass2: MyClass2,prop:KProperty<*>):Any?{
        return propValue
    }

    operator fun setValue(myClass2: MyClass2,prop:KProperty<*>,value: Any?){
        propValue=value
    }
}

fun <T> T.build(block: T.() -> Unit):T{
    block()
    return this
}

fun printString(str: String, block: (String) -> Unit) {
    block(str)
}

inline fun printStringInline(str: String, block: (String) -> Unit) {
    block(str)

}

class MySet<T>(val helperSet: HashSet<T>) : Set<T> {
    //通用委托(delegate)模式
    //方便重写部分方法与加入特有方法
    override val size: Int
    get() = helperSet.size
    override fun contains(element: T) = helperSet.contains(element)
    override fun containsAll(elements: Collection<T>) = helperSet.containsAll(elements)
    override fun isEmpty() = helperSet.isEmpty()
    override fun iterator() = helperSet.iterator()
}

//kotlin特有的类委托(delegate)
//关键字为by
class MySet2<T>(private val helperSet: HashSet<T>):Set<T> by helperSet{
    fun helloWorld()= println("Hello world")
    override fun isEmpty() = false
}

fun main(){
    val set:Set<Int>

    n1AndN2 { n1, n2 ->
        n1+n2
    }
    val num=10
    val myClass1=MyClass1<Int>()
    myClass1.method(num)

    val myClass2 = MyClass2()
    myClass2.method(num)

    myClass2.p

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

