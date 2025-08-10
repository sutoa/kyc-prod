import { MantineProvider, Container, Title } from '@mantine/core';
import { UserSearch } from './components/UserSearch';

function App() {
  return (
    <MantineProvider withGlobalStyles withNormalizeCSS>
      <Container size="sm" py="xl">
        <Title order={1} mb="lg" align="center">KYC User Profile Search</Title>
        <UserSearch />
      </Container>
    </MantineProvider>
  );
}

export default App;