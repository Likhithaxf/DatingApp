package com.test.datapp;

import java.util.List;

/**
 * Represents a user in the dating app.
 */
public class User {
    private int age;
    private String bio;
    private DealBreakers dealbreakers;
    private String gender;
    private String id;
    private List<String> liked_users;
    private String name;
    private String orientation;

    // Getters and Setters
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public DealBreakers getDealbreakers() {
        return dealbreakers;
    }

    public void setDealbreakers(DealBreakers dealbreakers) {
        this.dealbreakers = dealbreakers;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getLiked_users() {
        return liked_users;
    }

    public void setLiked_users(List<String> liked_users) {
        this.liked_users = liked_users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    // Inner class for DealBreakers
    public static class DealBreakers {
        private String drinking;
        private String relationship_type;
        private String religion;
        private String smoking;
        private String wants_kids;

        // Getters and Setters
        public String getDrinking() {
            return drinking;
        }

        public void setDrinking(String drinking) {
            this.drinking = drinking;
        }

        public String getRelationship_type() {
            return relationship_type;
        }

        public void setRelationship_type(String relationship_type) {
            this.relationship_type = relationship_type;
        }

        public String getReligion() {
            return religion;
        }

        public void setReligion(String religion) {
            this.religion = religion;
        }

        public String getSmoking() {
            return smoking;
        }

        public void setSmoking(String smoking) {
            this.smoking = smoking;
        }

        public String getWants_kids() {
            return wants_kids;
        }

        public void setWants_kids(String wants_kids) {
            this.wants_kids = wants_kids;
        }
    }
}

