package com.group.libraryapp.calculator
/**
 *수동으로 만든 테스트 코드의 단점
 * 1. 테스트 클래스와 메소드가 생길 때마다 메인 메소드에 수동을 코드를 작성해주어야 하고, 메인 메소드가 아주 커진다.
 * 개별적으로 실행하기도 어렵다.
 * 2. 테스트가 실패한 경우 무엇으 기대하였고, 어떤 잘못된 값이 들어와 실패했는지 알려주지 않는다.
 * 예외를 던지거나 try catch를 사용해야 하는 등 직접 구현해야할 부분이 많아 불편하다.
 * 3. 테스트 메소드별로 공통적으로 처리해야 하는 기능이 있다면, 메소드마다 중복이 생긴다.
 */
fun main(){
    val calculatorTest=CalculatorTest()
    calculatorTest.addTest()
    calculatorTest.minusTest()
    calculatorTest.multiTest()
    calculatorTest.dividTest()
    calculatorTest.dividExceptionTest()
}

class CalculatorTest{
    fun addTest(){
        //given
        val calculator=Calculator(5)
        //when
        calculator.add(3)
        //then
        if(calculator.number!=8) throw IllegalArgumentException()
    }

    fun minusTest(){
        //given
        val calculator=Calculator(8)

        //when
        calculator.minus(3)
        //then
        if(calculator.number!=5) throw IllegalArgumentException()
    }

    fun multiTest(){
        //given
        val calculator=Calculator(8)

        //when
        calculator.multiply(3)
        //then
        if(calculator.number!=24) throw IllegalArgumentException()
    }

    fun dividTest(){
        //given
        val calculator=Calculator(5)

        //when
        calculator.divide(2)
        //then
        if(calculator.number!=2) throw IllegalArgumentException()

    }

    fun dividExceptionTest(){
        val calculator = Calculator(5)
        try {
            calculator.divide(0)
        }catch (e: IllegalArgumentException){
            if(e.message!="0으로 나눌 수 없습니다.") throw IllegalStateException("메시지가 다릅니다.")
            return
        }catch (e: Exception) {
            throw IllegalStateException()
        }
        throw IllegalStateException("기대하는 예외가 발생하지 않았다.")
    }

}