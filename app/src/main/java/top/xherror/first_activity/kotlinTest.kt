package top.xherror.first_activity

fun eatFruitUseWith(){
    val list= listOf<String>("Apple","Banana","Pear")
    val result= with(StringBuilder()){
        append("Start eating fruits.\n")
        for (fruit in list){
            append("$fruit\n")
        }
        append("End!")
        toString()
    }
    println(result)
}

fun eatFruitUseRun(){
    val list= listOf<String>("Apple","Banana","Pear")
    val result= StringBuilder().run{
        append("Start eating fruits.\n")
        for (fruit in list){
            append("$fruit\n")
        }
        append("End!")
        toString()
    }
    println(result)
}

fun eatFruitUseApply(){
    val list= listOf<String>("Apple","Banana","Pear")
    val result= StringBuilder().apply{
        append("Start eating fruits.\n")
        for (fruit in list){
            append("$fruit\n")
        }
        append("End!")
        toString()
    }
    println(result)
}

fun main(){
    eatFruitUseApply()
}