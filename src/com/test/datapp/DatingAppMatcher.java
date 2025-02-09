package com.test.datapp;

import java.util.Arrays;
import java.util.List;

public class DatingAppMatcher {
   public static void main(String[] args) throws Exception {
       // Sample user data
	   GetUsersResponse usersResponse = MatchValidator.fetchUsersFromEndpoint();

	   System.out.println("get Users count: " + usersResponse.getCount());
       // Find best matches
       List<MatchedUsers> bestMatches = Matcher.findBestMatches(usersResponse.getUsers());
       System.out.println("Best Matches: " + bestMatches);

       // Validate matches using API endpint
       MatchValidator.validateMatches(bestMatches);
   }
}
