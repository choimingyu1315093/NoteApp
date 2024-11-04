package com.example.noteapp.playground

fun main() {
    val engine = Engine()
    val turboEngine = TurboEngine()
    val car = Car(engine, turboEngine)
    car.engine.start()
    car.turboEngine.start()
}

//의존성 주입은 클래스 내부에서 직접 객체를 생성하는 대신, 외부에서 객체를 주입받는 방식이다.
//Car 내부 코드는 건들지 않고, 여러 Engine들을 주입받아서 테스트할 수 있다.
class Car(val engine: Engine, val turboEngine: TurboEngine){
//    val engine = Engine() 내부에서 객체 생성
    fun startCar(){
        println("starting car...${engine.start()}")
    }
}

class Engine(){
    fun start(){
        println("Engine x started")
    }
}

class TurboEngine(){
    fun start(){
        println("Turbo engine X")
    }
}