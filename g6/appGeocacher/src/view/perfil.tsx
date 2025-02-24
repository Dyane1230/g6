import { useEffect, useState, useContext } from 'react';
import { StyleSheet, View, Text } from 'react-native';
import { Avatar } from 'react-native-paper';
import PantallaArtefacto from './pantallaArtefacto';
import Grafic from './grafic';
import { GetAllArtefactos } from '../dao/GetAllArtefactos';
import { GetArtefactoDeUsuario } from '../dao/GetArtefactoDeUsuario';
import { GetUsuario } from '../dao/GetUsuario';
import { AuthContext } from '../config/AuthContext';
import React from 'react';

export const Perfil = ({ navigation }) => {
  const [error, setError] = useState(null);
  const [allArtefactos, setAllArtefactos] = useState(0);
  const [artefactosUsuario, setArtefactosUsuario] = useState(0);
  const [nombreUsu, setNombreUsu] = useState("UserName");

  const { userId } = useContext(AuthContext);

  useEffect(() => {
    async function fetchData() {
      if (!userId) return; // Asegura que userId es válido antes de ejecutar

      
      console.log("PANTALLA PERFIL ID -----> ", userId);

       // Obtener datos del usuario
       const datosUsuario = await GetUsuario(userId);
       console.log("Datos del usuario obtenidos:", datosUsuario);
       
       if (datosUsuario && datosUsuario.nombre) {
         setNombreUsu(datosUsuario.nombre);
       } else {
         console.warn("No se encontró el nombre del usuario");
       }
      
      const datosArtefactos = await GetArtefactoDeUsuario(userId, "");
      console.log("artefactos de un usuario : --->", datosArtefactos)
      try {
        // Obtener todos los artefactos
        const datos = await GetAllArtefactos();
        setAllArtefactos(datos.length);
        console.log("NUMERO DE ARTEFACTOS ----> ", datos.length);

        // Obtener artefactos del usuario
        setArtefactosUsuario(datosArtefactos.length);
        

      } catch (error) {
        setError(error);
        console.error("Error obteniendo datos del perfil:", error);
      }
    }

    fetchData();
  }, [userId]); // Se ejecuta cuando userId cambia

  return (
    <View style={styles.container}>
      <View style={styles.userContainer}>
        <Avatar.Image
          size={84}
          source={require('../image/eeve.png')}
        />
        <Text style={styles.id}>{nombreUsu}</Text>
      </View>

      <View style={styles.row}>
        <Text style={styles.id}>Artefactos obtenidos: </Text>
        <Text>{artefactosUsuario} / {allArtefactos}</Text>
      </View>

      <PantallaArtefacto />
      
      <Grafic 
        value1={isNaN(Number(allArtefactos)) ? 0 : Number(allArtefactos)} 
        value2={isNaN(Number(artefactosUsuario)) ? 0 : Number(artefactosUsuario)} 
      />
    </View>
  );
};

export default Perfil;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
  },
  row: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    width: '90%',
  },
  id: {
    fontSize: 17,
    fontWeight: 'bold',
  },
  userContainer: {
    alignItems: 'center',
    justifyContent: 'center',
    width: '100%',
    marginVertical: 20,
  }
});
