package org.example.practice.impls;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Implement a function that’s able to calculate an actor or actresses
 * Bacon number. An actor or actress’s Bacon number is defined as the
 * degrees of separation he or she has from "Kevin Bacon" (actor) with respect to
 * films they’ve worked on together. The higher the Bacon number, the
 * further away from "Kevin Bacon" the actor is.
 *
 * E.g. "Kevin Bacon" (actor) was in "Beauty Shop" (film) with "Octavia Spencer" (actress). "Octavia
 * Spencer" was in "Gifted" (film) with "Chris Evans" (actor). Therefore, "Octavia Spencer" has
 * a Bacon number of 1, and "Chris Evans" has a Bacon number of 2.
 *
 * Assume I’ve given you a function that given a person, returns all
 * people that directly share a credit with that person:
 * - public static List<String> getSharedCredits(String person)
 */
public class BaconNumber {

    private Map<String, List<String>> sharedCreditsMap;

    public BaconNumber(Map<String, List<String>> sharedCreditsMap) {
        this.sharedCreditsMap = sharedCreditsMap;
    }

    public List<String> getSharedCredits(String person) {
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

        List<String> sharedCreditActors = getSharedCredits(person);

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
