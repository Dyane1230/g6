import AsyncStorage from '@react-native-async-storage/async-storage';
import { StyleSheet, Text, View, Image, Alert } from 'react-native';
import { TextInput } from 'react-native-paper';
import Usuario from '../model/Usuario';
import { useContext, useEffect, useState } from 'react';
import { AuthContext } from '../config/AuthContext';
import { PutUsuario } from '../dao/PutUsuario';
import { Avatar, Button } from 'react-native-paper'
import { GetUsuario } from '../dao/GetUsuario';
import React from 'react';

export const PantallaAjustes = ({ navigation }) => {
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
  const [error, setError] = useState(null);
  const [allArtefactos, setAllArtefactos] = useState(0);
  const [artefactosUsuario, setArtefactosUsuario] = useState(0);
  const [nombreUsu, setNombreUsu] = useState("UserName");

  const { userId } = useContext(AuthContext);

  useEffect(() => {
    async function fetchData() {
      if (!userId) return; // Asegura que userId es válido antes de ejecutar
      console.log("PANTALLA Ajustes ID -----> ", userId);
       // Obtener datos del usuario
      const datosUsuario = await GetUsuario(userId);
       setUser(datosUsuario);
       console.log("Datos del usuario obtenidos:", datosUsuario);
    }

    fetchData();
  }, [userId]); // Se ejecuta cuando userId cambia

  async function MOdificarUsuario() {
    if (!userId) return; // Asegura que userId es válido antes de ejecutar
    console.log("PANTALLA Ajustes ID -----> ", userId);
     // Obtener datos del usuario
      const datosUsuario = await PutUsuario(userId, user);
     setUser(datosUsuario);
     console.log("Datos del usuario obtenidos:", datosUsuario);
     Alert.alert("Usuario modificado");
  }
    const handleSave = () => {
        Alert.alert("Éxito", "Los cambios han sido guardados.");
      };
      const removeUser = async () => {
        try {
            await AsyncStorage.removeItem('user');
            console.log('Usuario eliminado de AsyncStorage');
        } catch (error) {
            console.error('Error al eliminar usuario', error);
        }
        };
      const handleLogout = () => {
        // Eliminar un valor
        removeUser();
        Alert.alert("Cierre de sesión", "Has cerrado sesión.");
        navigation.navigate("Login"); // Reemplaza con la pantalla de inicio de sesión
      };
    return (
      <View style={styles.maincontainer}>
        <View style={styles.card}>
        <Avatar.Image
          size={84}
          source={require('../image/eeve.png')}
        />
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


              <Button style={styles.button} mode="contained" onPress={MOdificarUsuario}>Guardar cambios </Button>

                      
              <Button  mode="contained" onPress={handleLogout} buttonColor= "red">Cerrar sesión</Button>

            </View>
        </View>
    
      
    );
}

export default PantallaAjustes;

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
        maxWidth: 400,
        backgroundColor: 'white',
        alignItems: 'center',
    },
    inputtext: {
        width: '100%',
        height:22,
        padding: 10,
        marginVertical: 8,
        backgroundColor: '#D9D9D9',
        borderRadius: 20,
        textAlign: 'center',
    },
    button: {
        //backgroundColor: '#2E1A79',
        borderRadius: 20,
        paddingVertical: 10,
        paddingHorizontal: 20,
        width: '100%',
        alignItems: 'center',
        marginVertical: 15,
    },
});