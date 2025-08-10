package com.example.kyc.dto;

import com.example.kyc.model.UserProfile;
import java.time.Instant;

public class UserProfileResponse {
    private String fullName;
    private String email;
    private String adoptionLocation;
    private String coeRegion;
    private Instant updatedAt;

    public static UserProfileResponse fromEntity(UserProfile entity) {
        UserProfileResponse response = new UserProfileResponse();
        response.setFullName(entity.getFullName());
        response.setEmail(entity.getEmail());
        response.setAdoptionLocation(entity.getAdoptionLocation());
        response.setCoeRegion(entity.getCoeRegion());
        response.setUpdatedAt(entity.getUpdatedAt());
        return response;
    }

    // Getters and Setters
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdoptionLocation() {
        return adoptionLocation;
    }

    public void setAdoptionLocation(String adoptionLocation) {
        this.adoptionLocation = adoptionLocation;
    }

    public String getCoeRegion() {
        return coeRegion;
    }

    public void setCoeRegion(String coeRegion) {
        this.coeRegion = coeRegion;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
