package org.example.practice.impls;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CompressPathTest {

    @Test
    public void testCompress() {
        CompressPath comp = new CompressPath();

        Assertions.assertEquals("s4e.c1m/p6s/c6t/c6r.m3a",
                comp.compress("stripe.com/payments/checkout/customer.maria"));
        Assertions.assertEquals("s8m/p6s/c6t/c12a",
                comp.compress("stripe.com/payments/checkout/customer.maria", 1));
        Assertions.assertEquals("s5n/h1w.to.w3e.a.j2a.p5m.in.o1e.d1y",
                comp.compress("section/how.to.write.a.java.program.in.one.day"));
        Assertions.assertEquals("s5n/h1w.to.w29y",
                comp.compress("section/how.to.write.a.java.program.in.one.day", 3));
    }
}
