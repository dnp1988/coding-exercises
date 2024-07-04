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

    public Integer getBaconNumberDFS(String actor) {
        Map<String, Integer> distances = new HashMap<>();
        Set<String> revisedActors = new HashSet<>();
        distances.put("Bacon", 0);
        return getBaconNumberDFS(actor, distances, revisedActors);
    }

    private Integer getBaconNumberDFS(String actor, Map<String, Integer> distances, Set<String> path) {
        Integer baconNumber = distances.get(actor);

        if(baconNumber != null) {
            return baconNumber;
        }

        List<String> sharedCreditActors = actorsSharingCreditsWith(actor);

        Integer lowerBaconNumber = Integer.MAX_VALUE;
        path.add(actor);

        for(String connectionActor : sharedCreditActors) {
            if(distances.containsKey(connectionActor) || !path.contains(connectionActor)) {
                Integer connectionBaconNumber = getBaconNumberDFS(connectionActor, distances, path);
                lowerBaconNumber = Math.min(lowerBaconNumber, connectionBaconNumber);
            }
        }

        distances.put(actor, lowerBaconNumber == Integer.MAX_VALUE ? Integer.MAX_VALUE : lowerBaconNumber + 1);
        return distances.get(actor);
    }

    public Integer getBaconNumberBFS(String actor) {
        if("Bacon".equals(actor)) {
            return 0;
        }
        Map<String, Integer> distances = new HashMap<>();
        Queue<String> unVisitedActors = new LinkedList<>();
        distances.put(actor, 0);

        unVisitedActors.add(actor);

        String currentActor = null;
        while(!unVisitedActors.isEmpty() && currentActor != "Bacon") {
            currentActor = unVisitedActors.poll();

            List<String> connections = actorsSharingCreditsWith(currentActor);
            Integer currentActorDistance = distances.get(currentActor);

            for(String connectionActor : connections) {
                Integer connectionActorDistance = distances.get(connectionActor);
                if(connectionActorDistance == null) {
                    distances.put(connectionActor, currentActorDistance + 1);
                    unVisitedActors.add(connectionActor);
                }
            }
        }

        return distances.getOrDefault("Bacon", Integer.MAX_VALUE);
    }

}
