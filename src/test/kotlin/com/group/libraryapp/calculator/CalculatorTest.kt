package com.group.libraryapp.calculator

fun main(){
    val calculatorTest=CalculatorTest()
    calculatorTest.addTest()
    calculatorTest.minusTest()
    calculatorTest.multiTest()
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

}