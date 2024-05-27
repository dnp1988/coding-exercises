package org.example.practice.impls;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
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

    public Integer getBaconNumberDFS(String person) {
        Map<String, Integer> memo = new HashMap<>();
        Set<String> revisedActors = new HashSet<>();
        memo.put("Bacon", 0);
        return getBaconNumberDFS(person, memo, revisedActors);
    }

    private Integer getBaconNumberDFS(String person, Map<String, Integer> memo, Set<String> visitedActors) {
        Integer memoBaconNumber = memo.get(person);

        if(memoBaconNumber != null) {
            return memoBaconNumber;
        }

        List<String> sharedCreditActors = actorsSharingCreditsWith(person);

        Integer lowerBaconNumber = Integer.MAX_VALUE;
        visitedActors.add(person);

        for(String actor : sharedCreditActors) {
            if(memo.containsKey(actor) || !visitedActors.contains(actor)) {
                Integer actorBaconNumber = getBaconNumberDFS(actor, memo, visitedActors);
                lowerBaconNumber = Math.min(lowerBaconNumber, actorBaconNumber);
            }
        }

        memo.put(person, lowerBaconNumber == Integer.MAX_VALUE ? Integer.MAX_VALUE : lowerBaconNumber + 1);
        return memo.get(person);
    }

    public Integer getBaconNumberBFS(String person) {
        if("Bacon".equals(person)) {
            return 0;
        }
        Map<String, Integer> distances = new HashMap<>();
        Set<String> visitedActors = new HashSet<>();
        Queue<String> unVisitedActors = new LinkedList<>();
        distances.put(person, 0);

        unVisitedActors.add(person);

        String currentActor = null;
        while(!unVisitedActors.isEmpty() && currentActor != "Bacon") {
            currentActor = unVisitedActors.poll();

            visitedActors.add(currentActor);
            List<String> connections = actorsSharingCreditsWith(currentActor);
            Integer currentActorDistance = distances.get(currentActor);

            for(String connectionActor : connections) {
                if(!visitedActors.contains(connectionActor)) {
                    unVisitedActors.add(connectionActor);

                    Integer connectionActorDistance = distances.get(connectionActor);
                    if(connectionActorDistance == null || currentActorDistance + 1 < connectionActorDistance) {
                        distances.put(connectionActor, currentActorDistance + 1);
                    }
                }
            }
        }

        return distances.getOrDefault("Bacon", Integer.MAX_VALUE);
    }

}
