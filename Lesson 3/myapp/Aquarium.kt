package example.myapp

import java.lang.Math.PI

open class Aquarium(length: Int = 100, width: Int = 20, height: Int = 40) {
    //dimensions in cm
    //primary constructors
    open var length : Int = length
    open var height : Int = height
    open var width : Int = width

    init {
        println("aquarium initializing")
    }
    /*
    init {
        println("Volume: ${width * length * height / 1000} litres")
        //execute logics inside ${}
    }
    */

    //secondary constructors
    constructor(numberOfFish : Int) : this(){
        // 2,000 cm^3 per fish + extra room so water doesn't spill
        //this() refers to first constructor Aquarium()
        val tank = numberOfFish * 2000 * 1.1
        height = (tank / (length * width)).toInt()
        //turn answer into int
    }


    fun printSize(){
        println(shape)
        println("Width: $width cm " +
                "Height: $height cm " +
                "Length: $length cm ")
        //1 litre = 1000 cm^3
        //println("Volume: $volume litres")
        println("Volume: $volume liters Water: $water liters (${water / volume * 100.0}% full)")
    }





    //computed property
    //volume var is public and can return int value with get() and set()
    open var volume: Int
        get() = width * height * length / 1000
        set(value){
            height = (value * 1000) / (width * length)
            //perform operation and stores in height var
        }

    open val shape = "rectangle"

    open var water : Double = 0.0
        get() = volume * 0.9












}


class TowerTank (override var height: Int, var diameter: Int): Aquarium(height = height, width = diameter, length = diameter) {
    override var volume: Int
        // ellipse area = π * r1 * r2
        get() = (width/2 * length/2 * height / 1000 * PI).toInt()
        set(value) {
            height = ((value * 1000 / PI) / (width/2 * length/2)).toInt()
        }

    override var water = volume * 0.8
    override val shape = "cylinder"
}


