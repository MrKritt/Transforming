interface Transformable {
    fun resize(zoom: Double)

    fun rotate(direction: RotateDirection, rotationPoint: Point)
}

enum class RotateDirection {
    clockwise, conterclockwise
}