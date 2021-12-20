import kotlin.math.pow

class Circle(override var centr: Point, var rad: Double) : Figure() {
    override fun area(): Double {
        return this.rad.pow(2.0) * Math.PI
    }
    override fun moveTo(point: Point) {
        this.centr = point
    }
    override fun resize(zoom: Double) {
        this.rad *= zoom
    }
    override fun toString(): String {
        return "Центр круга ${this.centr} радиус: ${this.rad} \n"
    }
    override fun rotate(direction: RotateDirection, topiont: Point) {
        if (direction == RotateDirection.clockwise) {
            this.centr.x = this.centr.y - topiont.y + topiont.x.also {
                this.centr.y = -1 * (this.centr.x - topiont.x) + topiont.y
            }
        } else {
            this.centr.x = -1 * (this.centr.y - topiont.y) + topiont.x.also {
                this.centr.y = this.centr.y - topiont.x + topiont.y
            }
        }
    }

}