fun main() {
    val rect = Rect(Point(3.0, 6.0), Point(2.0, 2.0))
    print(rect)
    rect.rotate(RotateDirection.conterclockwise, Point(4.0, 6.0))
    print("Поворачиваем прямоугольник: $rect")
    rect.resize(4.0)
    print("Изменяем размер прямоугольника: $rect")
    val square = Square(Point(10.0, 10.0), 4.0)
    println(square)
    square.rotate(RotateDirection.clockwise, Point(3.0, 3.0))
    print("Поверачиваем квадрат: $square\n")
    square.resize(5.0)
    print("Изменяем размер квадрата: $square")
    print("\n")
    val circle = Circle(Point(17.0, 17.0), 8.0)
    println(circle)
    circle.rotate(RotateDirection.clockwise, Point(5.0, 8.0))
    print("Вращаем круг: $circle\n")
    circle.resize(2.0)
    print("Изменяем размер круга: $circle")
}