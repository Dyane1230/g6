import AsyncStorage from '@react-native-async-storage/async-storage';
import Usuario from '../model/Usuario';

const apiUrl = process.env.EXPO_PUBLIC_API_URL;
const authApiUrl = process.env.EXPO_PUBLIC_AUTH_API_URL;

export const PostUserLogin = async ( email: string, pass:string ) => {
    try {
        const response = await fetch(`${apiUrl}/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ email, pass })
        });

        if (response.ok || response.status === 201) {
            const usuario = await response.json();
            return usuario;

        } else {
            //alert("Usuario no encontrado");
            return null;
        }
    } catch (error) {
        console.log('Error en la autenticación:', error);
        return null;
    }
};