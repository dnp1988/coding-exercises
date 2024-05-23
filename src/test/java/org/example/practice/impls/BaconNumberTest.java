package org.example.practice.impls;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class BaconNumberTest {

    @Test
    public void testBaconNumberTest() {
        BaconNumber baconNumber = new BaconNumber(Map.of(
                "ActorX", List.of("ActorY"),
                "ActorY", List.of("ActorX", "ActorZ"),
                "ActorZ", List.of("ActorY"),

                "ActorG", List.of("ActorA"),
                "ActorA", List.of("ActorG", "ActorB", "ActorC", "ActorD"),
                "ActorB", List.of("ActorA", "ActorC", "ActorD"),
                "ActorC", List.of("ActorA", "ActorB", "ActorD"),
                "ActorD", List.of("ActorA", "ActorB", "ActorC", "Bacon"),
                "Bacon", List.of("ActorD")
        ));

        Assertions.assertEquals(Integer.MAX_VALUE, baconNumber.getBaconNumber("ActorX"));
        Assertions.assertEquals(3, baconNumber.getBaconNumber("ActorG"));
        Assertions.assertEquals(2, baconNumber.getBaconNumber("ActorA"));
        Assertions.assertEquals(2, baconNumber.getBaconNumber("ActorB"));
        Assertions.assertEquals(2, baconNumber.getBaconNumber("ActorC"));
        Assertions.assertEquals(1, baconNumber.getBaconNumber("ActorD"));
        Assertions.assertEquals(0, baconNumber.getBaconNumber("Bacon"));
    }
}
