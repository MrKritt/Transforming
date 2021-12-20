class Square(private var topleft: Point, length: Double) : Figure() {
    private var meas: Double = length
    override var centr: Point = Point(0.0, 0.0)

    init {
        this.centr = Point(this.topleft.x + (meas / 2), this.topleft.y - (meas / 2))
    }
    override fun toString(): String {
        return "Центр квадрата ${this.centr} " + "верхний левый угол ${this.topleft})\n"
    }
    override fun area(): Double {
        return this.meas * this.meas
    }
    override fun moveTo(point: Point) {
        this.centr = point
        this.newPoint()
    }
    private fun newPoint() {
        this.topleft = Point(this.centr.x - this.meas / 2, this.centr.y + this.meas / 2)
    }
    override fun resize(zoom: Double) {
        this.meas *= zoom
        this.newPoint()
    }
    override fun rotate(direction: RotateDirection, rotationPoint: Point) {
        if (direction == RotateDirection.clockwise) {
            this.topleft.x = this.topleft.y - rotationPoint.y + rotationPoint.x
        } else {
            this.topleft.x = -1 * (this.topleft.y - rotationPoint.y) + rotationPoint.x
        }
        this.centr = Point(this.topleft.x + (meas / 2), this.topleft.y - (meas / 2))
    }

}