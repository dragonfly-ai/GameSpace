package ai.dragonfly.game.space

import ai.dragonfly.math.vector.Vector2

object TestGameSpace {
  def run(): Unit = {

//    val b1 = Body2Demo()
//    val b2 = Body2Demo()
//    print(if (b1.collidesWith(b2)) "collision" else "")
//
//    while (b1.collidesWith(b2)) {
//      //b1.rotate(b1.turnRate)
//      b1.accelerate()
//      b1.move
//    }
//
//    var iter = 0
//    while (!b1.collidesWith(b2) && iter < 100) {
//      print(if (b1.collidesWith(b2)) "collision" else ".")
//      b1.turnToward(b2)
//      b1.accelerate()
//      b1.move
//      println(s"${b1.position} ${b1.orientation} ")
//      iter = iter + 1
//    }

    val b1 = Body2Demo(position = new Vector2(4.0, 3.0))
    val b2 = Body2Demo(position = new Vector2(1.0, -2.0))

    for (i <- 0 until 100) {
      b1.turnToward(b2)
      b1.accelerate()
      b1.move
      println(b1.orientation + " " + b2.position + " " + b1.position.distanceTo(b2.position))
    }

    println(b1)

  }
}

case class Body2Demo (
  override val acceleration: Double = 0.1,
  override val maxSpeed: Double = 1.0,
  override val mass: Double = 1.0,
  override val creationFrame: Long = 0L,
  override val radius: Double = 5.0,
  override var speed: Double = 0.0,
  override var direction: Vector2 = new Vector2(1.0, 0),
  override var cumulativeDistance: Double = 0.0,
  override val orientation: Vector2 = new Vector2(-1.0, 0),
  override val turnRate: Double = Math.PI / 10.0,
  override val position: Vector2 = new Vector2(0.0, 0.0)
) extends Body2