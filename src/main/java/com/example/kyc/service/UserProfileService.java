package com.example.kyc.service;

import com.example.kyc.dto.UserProfileResponse;
import com.example.kyc.exception.UserNotFoundException;
import com.example.kyc.model.UserProfile;
import com.example.kyc.repository.UserProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserProfileService {
    private final UserProfileRepository userProfileRepository;

    public UserProfileService(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Transactional(readOnly = true)
    public UserProfileResponse findByEmail(String email) {
        UserProfile userProfile = userProfileRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("User not found: " + email));
        return UserProfileResponse.fromEntity(userProfile);
    }
}
