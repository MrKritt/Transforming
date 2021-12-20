import kotlin.math.abs

class Rect(private var topleft: Point, private var botright: Point) : Figure() {
    private var w: Double
    private var h: Double
    override var centr: Point = Point(0.0, 0.0)

    init {
        this.w = abs(topleft.x - botright.x)
        this.h = abs(topleft.y - botright.y)
        this.centr = Point((this.topleft.x + this.botright.x) / 2, (this.topleft.y + this.botright.y) / 2)
    }
    override fun toString(): String {
        return "Верхний левый угол прямоугольника ${this.topleft} " + "нижний правый ${this.botright} " + "ширина: ${this.w} высота: ${this.h} \n\n"
    }
    override fun area(): Double {
        return w * h
    }
    override fun moveTo(point: Point) {
        this.centr = point
        this.newPoints()
    }
    private fun newPoints(newPoints: ArrayList<Point>? = null) {
        if (newPoints == null) {
            this.topleft = Point(this.centr.x - this.w / 2, this.centr.y + this.h / 2)
            this.botright = Point(this.centr.x + this.w / 2, this.centr.y - this.h / 2)
        } else {
            var max: Point? = null
            var min: Point? = null
            for (i in 0 until newPoints.size) {
                if (min == null) {
                    min = newPoints[i]
                } else if (min.x < newPoints[i].x && min.y > newPoints[i].y) {
                    min = newPoints[i]
                }

                if (max == null) {
                    max = newPoints[i]
                } else if (max.x > newPoints[i].x && max.y < newPoints[i].y) {
                    max = newPoints[i]
                }
            }
            if (min != null) {
                this.topleft = min
            }
            if (max != null) {
                this.botright = max
            }
        }
    }
    private fun vector(rotationPoint: Point): ArrayList<Point> {
        val vectors = ArrayList<Point>()
        vectors.add(Point(this.topleft.x - rotationPoint.x, this.topleft.y - rotationPoint.y))
        vectors.add(Point(this.topleft.x + this.w - rotationPoint.x, this.topleft.y - rotationPoint.y))
        vectors.add(Point(this.botright.x - this.w - rotationPoint.x, this.botright.y - rotationPoint.y))
        vectors.add(Point(this.botright.x - rotationPoint.x, this.botright.y - rotationPoint.y))
        return vectors
    }
    override fun rotate(direction: RotateDirection, rotationPoint: Point) {
        val newPoints = ArrayList<Point>()
        if (direction == RotateDirection.clockwise) {
            for (vector in this.vector(rotationPoint)) {
                vector.x = vector.y - rotationPoint.x
                vector.y = vector.x * -1 - rotationPoint.y
                newPoints.add(vector)
            }
        } else {
            for (vector in this.vector(rotationPoint)) {
                val bufX = vector.x
                val bufY = vector.y
                vector.x = bufY * -1 + rotationPoint.x
                vector.y = bufX + rotationPoint.y
                newPoints.add(vector)
            }
        }
        this.w = this.h.also { this.h = this.w }
        this.centr = Point(
                (this.topleft.x + this.botright.x) / 2,
                (this.topleft.y + this.botright.y) / 2
        )
    }
    override fun resize(zoom: Double) {
        val vectors = this.vector(this.centr)
        for (vector in vectors) {
            vector.x *= zoom
            vector.y *= zoom
        }
        this.newPoints(vectors)
        this.w = abs(topleft.x - botright.x)
        this.h = abs(topleft.y - botright.y)
        this.newPoints()
    }

}