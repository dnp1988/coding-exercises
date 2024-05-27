package org.example.practice.impls;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class BaconNumberTest {

    private BaconNumber baconNumber;

    @BeforeEach
    public void setUp() {
        baconNumber = new BaconNumber(Map.of(
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
    }

    @Test
    public void testBaconNumberDFSTest() {
        Assertions.assertEquals(Integer.MAX_VALUE, baconNumber.getBaconNumberDFS("ActorX"));
        Assertions.assertEquals(3, baconNumber.getBaconNumberDFS("ActorG"));
        Assertions.assertEquals(2, baconNumber.getBaconNumberDFS("ActorA"));
        Assertions.assertEquals(2, baconNumber.getBaconNumberDFS("ActorB"));
        Assertions.assertEquals(2, baconNumber.getBaconNumberDFS("ActorC"));
        Assertions.assertEquals(1, baconNumber.getBaconNumberDFS("ActorD"));
        Assertions.assertEquals(0, baconNumber.getBaconNumberDFS("Bacon"));
    }

    @Test
    public void testBaconNumberBFSTest() {
        Assertions.assertEquals(Integer.MAX_VALUE, baconNumber.getBaconNumberBFS("ActorX"));
        Assertions.assertEquals(3, baconNumber.getBaconNumberBFS("ActorG"));
        Assertions.assertEquals(2, baconNumber.getBaconNumberBFS("ActorA"));
        Assertions.assertEquals(2, baconNumber.getBaconNumberBFS("ActorB"));
        Assertions.assertEquals(2, baconNumber.getBaconNumberBFS("ActorC"));
        Assertions.assertEquals(1, baconNumber.getBaconNumberBFS("ActorD"));
        Assertions.assertEquals(0, baconNumber.getBaconNumberBFS("Bacon"));
    }
}
