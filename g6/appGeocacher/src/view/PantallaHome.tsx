import { StatusBar } from 'expo-status-bar';
import { StyleSheet, View, TouchableOpacity } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs';
import Ionicons from 'react-native-vector-icons/Ionicons';
import Perfil from './perfil';
import PantallaTodosLosArtefactos from './PantallaTodosLosArtefactos';
import Mapa from './Mapa';
import PantallaCamara from './PantallaCamara';
import PantallaAjustes from './PantallaAjustes';

export const PantallaHome = ({ navigation }) =>  {
  const Tab = createBottomTabNavigator();
  const Stack = createNativeStackNavigator();

  return (
    //<NavigationContainer>
      <Stack.Navigator id={null}>
        <Stack.Screen name="Home" options={{ headerShown: false }}>
          {() => (
            <Tab.Navigator id={null}
              screenOptions={({ route, navigation }) => ({
                tabBarIcon: ({ focused, color, size }) => {
                  let iconName;

                  if (route.name === 'Perfil') {
                    iconName = focused ? 'person' : 'person-outline';
                  } else if (route.name === 'Mapa') {
                    iconName = focused ? 'map' : 'map-outline';
                  } else if (route.name === 'Artefactos') {
                    iconName = focused ? 'star' : 'star-outline';
                  } else if (route.name === 'Cámara') {
                    iconName = focused ? 'camera' : 'camera-outline';
                  }

                  return <Ionicons name={iconName} size={size} color={color} />;
                },
                tabBarActiveTintColor: '#fcdb38',
                tabBarInactiveTintColor: 'white',
                tabBarStyle: {
                  backgroundColor: '#b51a0e',
                },
                headerStyle: {
                  backgroundColor: '#b51a0e',
                },
                headerTintColor: 'white',
                headerRight: () =>
                  route.name === 'Perfil' ? (
                    <TouchableOpacity onPress={() => navigation.navigate('Ajustes')} style={{ marginRight: 15 }}>
                      <Ionicons name="settings-outline" size={24} color="white" />
                    </TouchableOpacity>
                  ) : null,
              })}
            >
              <Tab.Screen name="Perfil" component={Perfil} />
              <Tab.Screen name="Mapa" component={Mapa} />
              <Tab.Screen name="Artefactos" component={PantallaTodosLosArtefactos} />
              <Tab.Screen name="Cámara" component={PantallaCamara} />
            </Tab.Navigator>
          )}
        </Stack.Screen>
        <Stack.Screen name="Ajustes" component={PantallaAjustes} />
        
      </Stack.Navigator>
    //</NavigationContainer>
  );
}

export default PantallaHome;
const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});