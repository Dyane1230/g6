import { Text, View } from 'react-native';
import { useEffect, useState } from 'react';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import Login from './Login';
import PantallaHome from './PantallaHome';


export const HomeRouter = ( ) => {


  const Stack = createNativeStackNavigator();

    useEffect(() => {

    }, []);

    return (
        <Stack.Navigator initialRouteName="PantallaHome" id={null}>
            <Stack.Screen name="PantallaHome" component={PantallaHome} 
                options={{ title: 'HOME', headerShown: false }} />
        </Stack.Navigator>
    );
}

export default HomeRouter;