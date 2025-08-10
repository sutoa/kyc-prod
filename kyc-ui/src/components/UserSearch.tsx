import { useState } from 'react';
import { TextInput, Button, Paper, Text, Group } from '@mantine/core';
import { getUserByEmail } from '../api/userProfileApi';
import { UserProfile, ApiError } from '../types/UserProfile';

export function UserSearch() {
    const [email, setEmail] = useState('');
    const [userProfile, setUserProfile] = useState<UserProfile | null>(null);
    const [error, setError] = useState<string | null>(null);
    const [loading, setLoading] = useState(false);

    const handleSearch = async () => {
        if (!email) {
            setError('Please enter an email address');
            return;
        }

        setLoading(true);
        setError(null);
        setUserProfile(null);

        try {
            const data = await getUserByEmail(email);
            setUserProfile(data);
        } catch (err: any) {
            const apiError = err.response?.data as ApiError;
            setError(apiError?.message || 'An error occurred while fetching the user profile');
        } finally {
            setLoading(false);
        }
    };

    return (
        <Paper p="md" radius="md">
            <Group>
                <TextInput
                    placeholder="Enter email address"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    style={{ flex: 1 }}
                    error={error}
                />
                <Button onClick={handleSearch} loading={loading}>
                    Search
                </Button>
            </Group>

            {userProfile && (
                <Paper mt="md" p="md" radius="md" withBorder>
                    <Text size="lg" weight={500} mb="xs">{userProfile.fullName}</Text>
                    <Text><strong>Email:</strong> {userProfile.email}</Text>
                    <Text><strong>Adoption Location:</strong> {userProfile.adoptionLocation}</Text>
                    <Text><strong>COE Region:</strong> {userProfile.coeRegion}</Text>
                    <Text size="sm" color="dimmed" mt="xs">
                        Last updated: {new Date(userProfile.updatedAt).toLocaleString()}
                    </Text>
                </Paper>
            )}

            {error && !userProfile && (
                <Text color="red" mt="md">{error}</Text>
            )}
        </Paper>
    );
}