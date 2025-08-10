import axios from 'axios';
import { UserProfile } from '../types/UserProfile';

const API_BASE_URL = 'http://localhost:8080/api/v1';

export const getUserByEmail = async (email: string): Promise<UserProfile> => {
    const response = await axios.get(`${API_BASE_URL}/users/by-email`, {
        params: { email }
    });
    return response.data;
};