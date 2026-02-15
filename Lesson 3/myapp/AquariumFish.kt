package example.myapp

//abstracted superclass
abstract class AquariumFish{
    abstract val color: String
}

interface FishAction {
    fun eat()
}




//override data in subclasses
class Shark: AquariumFish(), FishAction{
    override val color = "grey"
    override fun eat(){
        println("hunt and eat fish")
    }
}

class Plecostomus: AquariumFish(), FishAction{
    override val color = "gold"
    override fun eat(){
        println("eat algae or get eaten")
    }
}




