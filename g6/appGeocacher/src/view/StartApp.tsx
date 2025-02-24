import React, { useEffect, useContext, useState } from 'react';
import { View, Image, Text, StyleSheet, ActivityIndicator } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { AuthContext } from '../config/AuthContext';
import Usuario from '../model/Usuario';

const StartApp = ({ navigation }) => {
    const { setLoggedIn, setUserId, setToken } = useContext(AuthContext);
    const [loading, setLoading] = useState(true);
    const [usu, setUsu] = useState(null);

    const getUserFromStorage = async () => {
        try {
            const storedUser = await AsyncStorage.getItem('user');
            return storedUser ? JSON.parse(storedUser) : null;
        } catch (error) {
            console.error('Error al obtener usuario', error);
            return null;
        }
    };

    useEffect(() => {
        const checkAuth = async () => {
            const usuarioGuardado = await getUserFromStorage();
            setUsu(usuarioGuardado);
            console.log("STARTAPP USUARIO GUARDADOOOO ---->", usuarioGuardado)
            if (!usuarioGuardado) {
                setTimeout(() => {
                    navigation.navigate("Login");
                  }, 5000); 
            } else {
                setTimeout(() => {
                    setUserId(usuarioGuardado.id);
                    setToken(usuarioGuardado.token);
                    setLoggedIn(true);
                    navigation.navigate("HomeRouter");
                  }, 5000);
            }
            setLoading(false);
        };

        checkAuth();
    }, []);

    if (loading) {
        return (
            <View style={styles.container}>
                <ActivityIndicator size="large" color="#b51a0e" />
            </View>
        );
    }

    return (
        <View style={styles.container}>
            <Image 
                source={require('../image/title.png')} 
                style={styles.pokedex} 
            />
            <Image 
                source={require('../image/pokedex2.png')} 
                style={styles.pokedex2} 
            />
            <Text style={styles.footerText}>Created and powered by G6</Text>
        </View>
    );
};

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center',
    },
    pokedex2: {
        width: 180,
        height: 180,
        resizeMode: 'contain',
    },
    pokedex: {
        width: 170,
        height: 100,
        resizeMode: 'contain',
        position: 'absolute',
        top: '25%',
    },
    footerText: {
        position: 'absolute',
        bottom: 20,
        color: 'gray',
    },
});

export default StartApp;
