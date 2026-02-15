package example.myapp
import java.lang.Math
import example.myapp.decor.makeDecorations
import example.myapp.decor.Direction
import example.myapp.decor.Choice

fun buildAquarium(){
    val myAquarium = Aquarium() //create an object named myAquarium with Aquarium class
    myAquarium.printSize()
    //default height and length

    val aquarium2 = Aquarium(width = 25)
    aquarium2.printSize()

    //default width
    val aquarium3 = Aquarium(height = 35, length = 110)
    aquarium3.printSize()

    // everything custom
    val aquarium4 = Aquarium(width = 25, height = 35, length = 110)
    aquarium4.printSize()

    println("\n")
    println("\n")

    val aquarium6 = Aquarium(length = 25, width = 25, height = 40)
    aquarium6.printSize()
    //println("Volume: ${aquarium6.width * aquarium6.length * aquarium6.height / 1000} litres")
//    aquarium6.volume = 70
//    aquarium6.printSize()

    val myTower = TowerTank(diameter = 25, height = 40)
    myTower.printSize()

}

fun makeFish(){
    val shark = Shark()
    val pleco = Plecostomus()

    println("Shark: ${shark.color}")
    shark.eat()
    println("Plecostomus: ${pleco.color}")
    pleco.eat()
}


fun main(){
    buildAquarium()

    println("\n")
    println("\n")

    makeFish()

    println("\n")
    println("\n")

    makeDecorations()

    println("\n")
    println("\n")

    println(Direction.EAST.name)
    println(Direction.EAST.ordinal)
    //ordinal is position in the enum
    println(Direction.EAST.degrees)


    println("\n")
    println("\n")

    println(Choice.name)
    Choice.showDescription("pick")
    Choice.showDescription("selection")

    println("\n")
    println("\n")

    val fish = listOf("Shark", "Tuna", "Mackerel")

    //pair
    val equipment = "fish net" to "catching fish"
    println("${equipment.first} used for " +
            "${equipment.second}")


    println("\n")
    println("\n")

    //triple
    val numbers = Triple(6, 9, 42)
    println(numbers.toString())
    println(numbers.toList())

    println("\n")
    println("\n")

    //pair inside pair
    val equipment2 = ("fish net" to "catching fish") to "equipment"
    println("${equipment2.first} is ${equipment2.second}\n")
    println("${equipment2.first.second}")

    println("\n")
    println("\n")
    //pair destructuring
    val equipment3 = "fish net" to "catching fish"
    val (tool, use) = equipment3
    println("$tool is used for $use")

    println("\n")
    println("\n")

    //triple destructuring
    val numbers4 = Triple(6, 9, 42)
    val (n1, n2, n3) = numbers4
    println("$n1 $n2 $n3")



    //collections
    //list
    val list = listOf(1, 5, 3, 4)
    println(list.sum())

    val list2 = listOf("a", "bbb", "cc")
    println(list2.sumOf { it.length })


    for(s in list2.listIterator()){
        println("$s")
    }

    //hashmap
    val scientific = hashMapOf("guppy" to
            "poecilia reticulata", "catfish"
            to "corydoras", "zebra fish" to
            "danio rerio" )
    println (scientific.get("guppy"))
    println(scientific.getOrDefault("swordtail", "sorry, I don't know"))
    println(scientific.getOrElse("swordtail") {"sorry, I don't know"})
    //whatever codes inside {} gets executed
}