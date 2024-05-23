package org.example.practice.impls;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Implement a function that calculates an actor's Bacon number.
 * The Bacon number is defined as the degrees of separation the actor has from "Kevin Bacon" with respect to
 * films they have both been in.
 *
 * Following method is supposed to return all actors that directly share a credit with a given person:
 * - List<String> actorsSharingCreditsWith(String person)
 */
public class BaconNumber {

    private Map<String, List<String>> sharedCreditsMap;

    public BaconNumber(Map<String, List<String>> sharedCreditsMap) {
        this.sharedCreditsMap = sharedCreditsMap;
    }

    public List<String> actorsSharingCreditsWith(String person) {
        return sharedCreditsMap.get(person);
    }

    public Integer getBaconNumber(String person) {
        Map<String, Integer> memo = new HashMap<>();
        Set<String> revisedActors = new HashSet<>();
        memo.put("Bacon", 0);
        return getBaconNumber(person, memo, revisedActors);
    }

    private Integer getBaconNumber(String person, Map<String, Integer> memo, Set<String> revisedActors) {
        Integer memoBaconNumber = memo.get(person);

        if(memoBaconNumber != null) {
            return memoBaconNumber;
        }

        List<String> sharedCreditActors = actorsSharingCreditsWith(person);

        Integer lowerBaconNumber = Integer.MAX_VALUE;
        revisedActors.add(person);

        for(String actor : sharedCreditActors) {
            if(memo.containsKey(actor) || !revisedActors.contains(actor)) {
                Integer actorBaconNumber = getBaconNumber(actor, memo, revisedActors);
                lowerBaconNumber = Math.min(lowerBaconNumber, actorBaconNumber);
            }
        }

        memo.put(person, lowerBaconNumber == Integer.MAX_VALUE ? Integer.MAX_VALUE : lowerBaconNumber + 1);
        return memo.get(person);
    }
}
