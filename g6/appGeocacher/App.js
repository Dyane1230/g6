import { SafeAreaProvider } from 'react-native-safe-area-context';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import StartApp from './src/view/StartApp';
import Login from './src/view/Login';
import HomeRouter from './src/view/HomeRouter';
import Registro from './src/view/Registro';
import { AuthProvider } from './src/config/AuthContext';
import { PaperProvider } from 'react-native-paper';

export default function App() {
  const Stack = createNativeStackNavigator();

  return (
    <AuthProvider>
      <SafeAreaProvider>
        <PaperProvider>
          <NavigationContainer>
            <Stack.Navigator initialRouteName="StartApp" screenOptions={{ headerShown: false }}>
              <Stack.Screen name="StartApp" component={StartApp} />
              <Stack.Screen name="Login" component={Login} />
              <Stack.Screen name="Registro" component={Registro}/>
              <Stack.Screen name="HomeRouter" component={HomeRouter}/>
            </Stack.Navigator>
          </NavigationContainer>
        </PaperProvider>
      </SafeAreaProvider>
    </AuthProvider>
  );
}
