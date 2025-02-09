package com.test.datapp;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Implements the matching algorithm based on weighted factors.
 */
class Matcher {
    // Define weights for different matching criteria
    private static final double RELIGION_WEIGHT = 0.3;
    private static final double LIFESTYLE_WEIGHT = 0.25;
    private static final double INTERESTS_WEIGHT = 0.2;
    private static final double PERSONALITY_WEIGHT = 0.15;
    private static final double LOCATION_WEIGHT = 0.1;

    /**
     * Calculates compatibility between two users based on weighted factors.
     * @param user1 The first user
     * @param user2 The second user
     * @return A compatibility score between 0 and 1
     */
    public static double calculateCompatibility(User user1, User user2) {
        double score = 0;
        
        // Check if religion matches
        if (user1.getDealbreakers().getReligion().equalsIgnoreCase(user2.getDealbreakers().getReligion())) {
            score += RELIGION_WEIGHT;
        }

        // Check if lifestyle matches
        int ls = 0;
        if (user1.getDealbreakers().getDrinking().equalsIgnoreCase(user2.getDealbreakers().getDrinking())) {
        	ls = ls+1;
        }
        
        if (user1.getDealbreakers().getSmoking().equalsIgnoreCase(user2.getDealbreakers().getSmoking())) {
        	ls = ls+1;
        }
        
        score += (ls/2)*LIFESTYLE_WEIGHT;
        
     // Calculate interest similarity
        int i = 0;
        if (user1.getDealbreakers().getRelationship_type().equalsIgnoreCase(user2.getDealbreakers().getRelationship_type())) {
            i=i+1;
        }
        
        if (user1.getDealbreakers().getWants_kids().equalsIgnoreCase(user2.getDealbreakers().getWants_kids())) {
        	i=i+1;
        }
        score += (i/2)*INTERESTS_WEIGHT;
       
        // Check if personalities match
        int pi=0;
        if (Math.abs( user1.getAge() - user2.getAge()) <=3) {
        	pi=pi+1;
        }
        
        if (!user1.getGender().equals(user2.getGender())) {
        	pi=pi+1;
        }
        score += (pi/2)*PERSONALITY_WEIGHT;
        // No user location from the payload
		/*
		 * if (user1.location.equalsIgnoreCase(user2.location)) { score +=
		 * LOCATION_WEIGHT; }
		 */
        
        return score;
    }
    
    /**
     * Finds the best matches from a list of users based on compatibility scores.
     * @param users List of users
     * @return A list of best match pairs (IDs)
     */
    public static List<MatchedUsers> findBestMatches(List<User> users) {
        List<MatchedUsers> matches = new ArrayList<>();

        // Compare each user with every other user
        for (int i = 0; i < users.size(); i++) {
            for (int j = i + 1; j < users.size(); j++) {
                User user1 = users.get(i);
                User user2 = users.get(j);
                double compatibility = 0;
                compatibility = calculateCompatibility(user1, user2);

                // Only consider matches with a score above 0.5 (50% compatibility)
                if (compatibility >= 0.5) {
                	MatchedUsers match = new MatchedUsers();
                	match.setUser1_id(user1.getId());
                	match.setUser2_id(user2.getId());
                	match.setName1(user1.getName());
                	match.setName2(user2.getName());
                	match.setScore(compatibility);	
                    matches.add(match);
                   
                }
            }
        }

        // Sort matches in descending order based on compatibility score
        matches.sort((pair1, pair2) -> 
            Double.compare(pair2.getScore(), 
                          pair1.getScore())
        );

        return matches;
    }
}




 //Main class to run the matching algorithm and validate the results.

