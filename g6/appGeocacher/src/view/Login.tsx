import { useContext, useState } from 'react';
import { View, Image, Text, StyleSheet, TouchableOpacity } from 'react-native';
import { TextInput } from 'react-native-paper';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { AuthContext } from '../config/AuthContext';
import { PostUserLogin } from '../dao/PostUsuarioLogin';
import Usuario from '../model/Usuario';

export const Login = ({ navigation }) => {
    const [usu, setUsu] = useState(new Usuario());
    const { setLoggedIn, setUserId, setToken } = useContext(AuthContext);

    const storeUser = async (user) => {
        try {
            await AsyncStorage.setItem('user', JSON.stringify(user));
            console.log('Usuario guardado en AsyncStorage:', user);
        } catch (error) {
            console.error('Error al guardar usuario', error);
        }
    };

    const handleLogin = async () => {
        try {
            const usuario = await PostUserLogin(usu.email, usu.pass);

            if (!usuario) {
                alert("Credenciales incorrectas.");
                return;
            }

            setUserId(usuario.id);
            setLoggedIn(true);
            setToken(usuario.token);

            // Esperar que se guarde el usuario antes de navegar
            await storeUser(usuario);

            navigation.navigate("HomeRouter");
        } catch (error) {
            console.error('Error en el proceso de login:', error);
        }
    };

    return (
        <View style={styles.maincontainer}>
            <View style={styles.card}>
                <Image 
                    style={styles.imageLogo}
                    source={require('../image/title.png')} 
                />
                <Text style={styles.title}>Accede a tu cuenta:</Text>
                <TextInput
                    placeholder='Correo electrónico:'
                    placeholderTextColor="black"
                    style={styles.inputtext}
                    onChangeText={(e) => setUsu({ ...usu, email: e })}
                />
                <TextInput
                    placeholder='Contraseña:'
                    placeholderTextColor="black"
                    style={styles.inputtext}
                    secureTextEntry
                    onChangeText={(e) => setUsu({ ...usu, pass: e })}
                />
                <TouchableOpacity style={styles.button} onPress={handleLogin}>
                    <Text style={styles.buttonText}>LOGIN</Text>
                </TouchableOpacity>
            </View>
            <TouchableOpacity style={styles.button2} onPress={() => navigation.navigate("Registro")}>
                <Text style={styles.buttonText}>REGISTRO</Text>
            </TouchableOpacity>
        </View>
    );
};

const styles = StyleSheet.create({
    maincontainer: {
        flex: 1,
        backgroundColor: '#fff',
        alignItems: 'center',
        justifyContent: 'center',
        padding: 20,
    },
    card: {
        borderRadius: 10,
        padding: 20,
        width: '100%',
        maxWidth: 350,
        backgroundColor: 'white',
        alignItems: 'center',
    },
    imageLogo: {
        width: 180,
        height: 80,
        marginBottom: 20,
        resizeMode: "contain",
    },
    title: {
        fontSize: 18,
        fontWeight: 'bold',
        marginBottom: 15,
    },
    inputtext: {
        width: '100%',
        height: 35,
        padding: 10,
        marginVertical: 10,
        backgroundColor: '#D9D9D9',
        borderRadius: 20,
        textAlign: 'center',
    },
    button: {
        backgroundColor: '#2E1A79',
        borderRadius: 20,
        paddingVertical: 10,
        paddingHorizontal: 20,
        width: '90%',
        height: 50,
        alignItems: 'center',
        marginVertical: 5,
    },
    button2: {
        backgroundColor: '#fbc02d',
        borderRadius: 20,
        paddingVertical: 10,
        paddingHorizontal: 20,
        width: '80%',
        alignItems: 'center',
        marginVertical: 5,
        height: 50,
    },
    buttonText: {
        color: 'white',
        fontSize: 20,
        fontWeight: 'bold',
    },
});

export default Login;