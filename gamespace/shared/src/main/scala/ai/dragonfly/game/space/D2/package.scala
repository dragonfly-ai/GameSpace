package ai.dragonfly.game.space

import ai.dragonfly.game.{Massive, Temporal, Voluminous}
import ai.dragonfly.math.vector.Vector2

package object D2 {

}

trait Positioned2 {
  val position: Vector2
}

trait Oriented2 extends Positioned2 {
  val orientation: Vector2
  val turnRate: Double

  def rotate(angle: Double): Oriented2 = {
    orientation.rotateDegrees(angle)
    this
  }

  def turnToward(target: Positioned2): Oriented2 = {

    val relativePosition = target.position.copy().subtract(position).normalize()
    val sign = orientation.pseudoCross(relativePosition)
    val dTheta = Math.acos(relativePosition.dot(orientation))

    if ( dTheta > turnRate ) { orientation.rotate(sign * turnRate) }
    else { orientation.rotate(sign * dTheta) }
    //println(dTheta)
    //Math.abs(dTheta)
    this

  }

}

trait Mobile2 extends Positioned2 {
  var speed: Double
  var direction: Vector2
  var cumulativeDistance: Double
  def move: Mobile2 = {
    cumulativeDistance = cumulativeDistance + speed
    position.add(direction.copy().scale(speed))
    this
  }
}


trait Accelerated2 extends Mobile2 with Oriented2 {
  val acceleration: Double
  val maxSpeed: Double

  def accelerate(reverse: Boolean = false): Accelerated2 = {
    val dir = direction.copy() //.asInstanceOf[Vector2]
    dir.scale(speed)
    val or = orientation.copy()
    or.scale(acceleration)
    if (reverse) { dir.subtract(or) } else { dir.add(or) }
    speed = dir.magnitude()
    dir.divide(speed)
    if (speed > maxSpeed) { speed = maxSpeed }
    else if (speed < 0) { speed = 0 }
    direction = dir.asInstanceOf[Vector2]
    this
  }

  def decelerate: Accelerated2 = accelerate(true)
}

trait Body2 extends Accelerated2 with Temporal with Massive with Voluminous {
  def collidesWith(body: Body2): Boolean = position.distanceSquaredTo(body.position) <= radiusSquared + body.radiusSquared
}