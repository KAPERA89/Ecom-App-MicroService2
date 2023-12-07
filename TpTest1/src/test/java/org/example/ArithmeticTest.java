package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArithmeticTest {

//    @Test
//    public void division() {
//        assertTrue(3.0 == Arithmetic.division(18,6));
//        assertTrue(4.0 == Arithmetic.division(24,6));
//        assertTrue(-1 == Arithmetic.division(18,0));
//    }
//
//    @Test
//    public void reste() {x
//        assertTrue(2 == Arithmetic.reste(20,6));
//        assertTrue(1 == Arithmetic.reste(21,10));
//
//    }

    @Test
    public void testBadDenominator(){
        assertThrows(Exception.class, () -> {
            float x = Arithmetic.division(1,0);
        });
    }

    @Test
    public void testBeDenominator(){
        assertDoesNotThrow(() -> Arithmetic.division(1,2));
    }
}