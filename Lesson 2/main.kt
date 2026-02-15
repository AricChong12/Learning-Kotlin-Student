import java.util.*

fun printHello(){
    println("Hello World")
}


fun cat(args: Array<String>){
    println("Hello, ${args[0]}")
}


fun randomDay() : String{
    val week = arrayOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", " Sunday")
    return week[Random().nextInt(week.size)] 
    //Random().nextInt(week.size) generates random nums in range of 0 to 6
}


//takes day as string argument and outputs string
fun fishFood (day : String) : String {
    return when (day) {
        "Monday" -> "flakes"
        "Wednesday" -> "redworms"
        "Thursday" -> "granules"
        "Friday" -> "mosquitoes"
        "Sunday" -> "plankton"
        else -> "nothing"
    }
}


//day params as string, temperature params as int default 22, dirty params as int as 20, return boolean

// fun shouldChangeWater (day: String, temperature: Int = 22, dirty: Int = 20): Boolean {
//     return when {
//         temperature > 30 -> true
//         dirty > 30 -> true
//         day == "Sunday" ->  true
//         else -> false
//     }
// }

fun shouldChangeWater (day: String, temperature: Int = 22, dirty: Int = 20): Boolean {
    return when {
        isTooHot(temperature) -> true
        isDirty(dirty) -> true
        isSunday(day) -> true
        else  -> false
    }
}

fun feedTheFish() {
    val day = randomDay()
    val food = fishFood(day)
    println ("Today is $day and the fish eat $food")
    println("Change water: ${shouldChangeWater(day)}")
    //wraps the output of shouldChangeWater(day) into ${} to run logic
}


fun swim(speed : String = "fast"){
    println("swimming $speed")
}

//compact funcs
//temperature as int params and check if that params > 30
fun isTooHot(temperature : Int) = temperature > 30
fun isDirty(dirty : Int) = dirty > 30
fun isSunday(day : String) = day == "Sunday"
//asigns day to Sunday


val decorations = listOf("rock", "pagoda", "plastic plant", "alligator", "flowerpot")


fun main() {
  printHello()
  cat(arrayOf("Kotlin"))
  
  val isUnit = println("This is an expression")
  println(isUnit)
  
  val temp = 10
  val isHot = if (temp > 50) true else false
  println(isHot)
  
  
  //string template
  //the water temperature is (check if temp > 50 if yes print too warm if not print OK)
  val msg = "The water temperature is ${ if (temp > 50) "too warm" else "OK" }"
  println(msg)
  
  feedTheFish()
  
  swim()
  swim("slow")
  swim("turtle-like")
  
  println(decorations.filter {it[0] == 'p'})
  //it is a container var for each item, it[0] first char of the str
  //filter {}
  
  //eager
  val eager = decorations.filter {it[0] == 'p'}
  println("eager: $eager")
  
  //lazy, will wait till asked to evaluate
  val filtered = decorations.asSequence().filter {it[0] == 'p'}
  println("filtered: $filtered")
  
  //force evaluation of lazy
  val newList = filtered.toList()
  println("new list: $newList")
  
  //tips: lazy ask to evalute first then force evaluation
  
  
  val lazyMap = decorations.asSequence().map {
      println("access: $it")
      it
  }
  
    println("lazy: $lazyMap")
    println("-----")
    println("first: ${lazyMap.first()}")
    println("-----")
    println("all: ${lazyMap.toList()}")
  
    
    
    
    
    val lazyMap2 = decorations.asSequence().filter {it[0] == 'p'}.map {
        println("access: $it")
        it
    }
    println("-----")
    println("filtered: ${lazyMap2.toList()}")
    
    
    
    
    
    
    
    
    //flattening of list
    val mysports = listOf("basketball", "fishing", "running")
    val myplayers = listOf("LeBron James", "Ernest Hemingway", "Usain Bolt")
    val mycities = listOf("Los Angeles", "Chicago", "Jamaica")
    val mylist = listOf(mysports, myplayers, mycities)     
    // list of lists
    println("-----")
    println("Flat: ${mylist.flatten()}")
    //Flat: [basketball, fishing, running, LeBron James, Ernest Hemingway, Usain Bolt, Los Angeles, Chicago, Jamaica]
  
  
  //lambdas funcs
  var dirtyLevel = 20
  //init dirty as var then dirty / 2
  var waterFilter = {dirty : Int -> dirty / 2}
  println(waterFilter(dirtyLevel))
  //call waterFilter in println() and inserted argument of dirtyLevel in the waterFilter lambdas func
  
  //or
  val waterFilter2: (Int) -> Int = { dirty -> dirty / 2 }
  //takes int and outputs int
  
  
  //high order funcs (takes other funcs as params)
  fun updateDirty(dirty : Int, operation : (Int) -> Int) : Int{
      return operation(dirty)
  }
  //(Int) -> Int indicates a lambdas func is there as params
  
  //call high order funcs
  println(updateDirty(30, waterFilter))
  
  fun increaseDirty(start : Int) = start + 1
  println(updateDirty(15, ::increaseDirty))
  // :: is Pass the function itself, not the result of calling it.
  
  
  var dirtyLevel2 = 19
  dirtyLevel2 = updateDirty(dirtyLevel2) {dirtyLevel2 -> dirtyLevel2 + 23}
  println(dirtyLevel2)
  //{dirtyLevel2 -> dirtyLevel2 + 23} is lambdas params
  
  
  
  
  
  
  
  
  
}