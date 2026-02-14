

fun printHello(){
    println("Hello World")
}

val i : Int = 6
val b1 = i.toByte()

val numberOfFish = 5
val numberOfPlanets = 12

val fish = 50

val marbles : Int? = null

var fishFoodTreats = 6

fun main(){
    printHello()
    println(6*50)
    println(2.times(3))
    println(b1)
    println("I have $numberOfFish and $numberOfPlanets plants")
    
    if(numberOfFish > numberOfPlanets){
        println("Good ratio")
    }else{
        println("Unhealthy ratio")
    }
    
    if(fish in 1..100){ //range
        println(fish)
    }
    
    if (numberOfFish == 0) {
        println("Empty tank")
    } else if (numberOfFish < 40) {
        println("Got fish!")
    } else {
        println("That's a lot of fish!")
    }
    
    
    //when statements is like switch statements
    when(numberOfFish){
        0 -> println("Empty tank")
        in 1..39 -> println("Got fish")
        else -> println("Thats a lot of fish")
    }
    
    println(marbles)
    
    //if FishFoodTreats is not null decrement 1 if yes its
    // value 0
    fishFoodTreats = fishFoodTreats?.dec() ?: 0
    println(fishFoodTreats)
    
    val school = listOf("mackerel", "trout", "halibut")
    println(school)
    
    val myList = mutableListOf("tuna", "salmon", "shark")
    myList.remove("shark")
    println(myList)
    
    
    val schoolfish = arrayOf("shark", "salmon", "minnow")
    println(java.util.Arrays.toString(schoolfish))
    
    val numbers = intArrayOf(1,2,3)
    val numbers3 = intArrayOf(4,5,6)
    val foo2 = numbers3 + numbers // [4, 5, 6, 1, 2, 3]
    println(foo2[5])
    
    val numbers1 = intArrayOf(1,2,3)
    val oceans = listOf("Atlantic", "Pacific")
    val oddList = listOf(numbers1, oceans, "salmon")
    
    println(oceans)
    println(oddList)
    
    val array = Array (5) { it * 3 }
    println(java.util.Arrays.toString(array))
    
    
    for(element in schoolfish){
        print(element + " ")
    }
    
    for((index, element) in schoolfish.withIndex()){
        println("Items at $index is $element")
    }
    
    for (i in 1..5) print(i)
    //12345

    for (i in 5 downTo 1) print(i)
    //54321

    for (i in 3..6 step 2) print(i)
    //35

    for (i in 'd'..'g') print(i)
    //defg
    
    var bubbles = 0
    while (bubbles < 50) {
        bubbles++
    }
    println("$bubbles bubbles in the water\n")

    do {
        bubbles--
    } while (bubbles > 50)
    println("$bubbles bubbles in the water\n")

    repeat(2) {
        println("A fish is swimming")
    }
    
    
    
    
    
    
    
    
}













