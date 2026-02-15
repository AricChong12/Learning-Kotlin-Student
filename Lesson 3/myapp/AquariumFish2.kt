package example.myapp

interface FishColor {
    val color: String
}

//singleton
//knows as helper class that implements interface
object GoldColor: FishColor {
    override val color = "gold"
}

//interface delegation
//set default color to gold
class Plecostomus2(fishColor: FishColor = GoldColor):
    FishAction by PrintingFishAction("eat algae")
    , FishColor by fishColor





class Shark2: FishAction, FishColor {
    override val color = "grey"
    override fun eat() {
        println("hunt and eat fish")
    }
}


class PrintingFishAction(val food: String) : FishAction{
    override fun eat(){
        println(food)
    }
}
















