import AsyncStorage from '@react-native-async-storage/async-storage';
import Usuario from '../model/Usuario';

const apiUrl = process.env.EXPO_PUBLIC_API_URL;
const authApiUrl = process.env.EXPO_PUBLIC_AUTH_API_URL;

export const PostUsuario = async ( usuario : Usuario ) => {
    try {
        const response = await fetch(`${apiUrl}/usuario`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(usuario)
        });

        if (response.ok || response.status === 201 || response.status === 20 ) {
            const usuario = await response.json();
            return usuario;

        } else {
            alert("Error al registrar usuario");
            return null;
        }
    } catch (error) {
        console.log('Error en la autenticación:', error);
        return null;
    }
};