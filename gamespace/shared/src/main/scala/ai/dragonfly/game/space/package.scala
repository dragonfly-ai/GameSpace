package ai.dragonfly.game

package object space {

}

trait Temporal {
  val creationFrame: Long
}

trait Massive {
  val mass: Double
}

trait Voluminous {
  val radius: Double
  val radiusSquared: Double = radius * radius
}