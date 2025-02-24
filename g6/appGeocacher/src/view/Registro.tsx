import React, { useState } from 'react';
import { View, Text, TextInput, StyleSheet, Image, TouchableOpacity } from 'react-native';
import { PostUsuario } from '../dao/PostUsuario';
import Usuario from '../model/Usuario';

const Registro = ({ navigation }) => {
    const [user, setUser] = useState<Usuario>({
        nombre: '',
        email: '',
        username: '',
        pass: '',
        rol:0,
        fecha_registro:'',
        id:0,
        foto_perfil:'',
        estado:0,
        biografia:'', 
        token:"",
    });


    async function registrarUsuario() {
        const usuario = await PostUsuario(user);
        navigation.navigate("Login");
    }

    return (
        <View style={styles.maincontainer}>
            <View style={styles.card}>
                <Image 
                    style={styles.imageLogo}
                    source={{ uri: 'https://static.wikia.nocookie.net/doblaje/images/4/4b/Pok%C3%A9dex_logo.png/revision/latest?cb=20231114185508&path-prefix=es' }} 
                />

                <Text style={styles.title}>Crea tu cuenta:</Text>

                <TextInput
                    placeholder='Nombre completo:'
                    placeholderTextColor="black"
                    value={user.nombre}
                    style={styles.inputtext}
                    onChangeText={(e) => setUser({ ...user, nombre: e })}
                />

                <TextInput
                    placeholder='Correo electrónico:'
                    placeholderTextColor="black"
                    value={user.email}
                    style={styles.inputtext}
                    onChangeText={(e) => setUser({ ...user, email: e })}
                />

                <TextInput
                    placeholder='Username:'
                    placeholderTextColor="black"
                    value={user.username}
                    style={styles.inputtext}
                    onChangeText={(e) => setUser({ ...user, username: e })}
                />

                <TextInput
                    placeholder='Contraseña:'
                    placeholderTextColor="black"
                    value={user.pass}
                    style={styles.inputtext}
                    secureTextEntry
                    onChangeText={(e) => setUser({ ...user, pass: e })}
                />


                <TouchableOpacity style={styles.buttonRegister} onPress={registrarUsuario}>
                    <Text style={styles.buttonRegisterText}>REGISTER</Text>
                </TouchableOpacity>

                <TouchableOpacity style={styles.button} onPress= {() => navigation.navigate("Login")} >
                    <Text style={styles.buttonText}>LOGIN</Text>
                </TouchableOpacity>

            </View>
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
        padding: 10,
        marginVertical: 8,
        backgroundColor: '#D9D9D9',
        borderRadius: 20,
        textAlign: 'center',
    },
    button: {
        backgroundColor: '#2E1A79',
        borderRadius: 20,
        paddingVertical: 10,
        paddingHorizontal: 20,
        width: '100%',
        alignItems: 'center',
        marginVertical: 5,
    },
    buttonText: {
        color: 'white',
        fontSize: 16,
        fontWeight: 'bold',
    },
    buttonRegister: {
        backgroundColor: '#FFCC33',
        borderRadius: 20,
        paddingVertical: 10,
        paddingHorizontal: 20,
        width: '100%',
        alignItems: 'center',
        marginVertical: 5,
    },
    buttonRegisterText: {
        color: 'white',
        fontSize: 16,
        fontWeight: 'bold',
    }
});

export default Registro;