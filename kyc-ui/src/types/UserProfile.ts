export interface UserProfile {
    fullName: string;
    email: string;
    adoptionLocation: string;
    coeRegion: string;
    updatedAt: string;
}

export interface ApiError {
    timestamp: string;
    status: number;
    error: string;
    message: string;
    path: string;
}