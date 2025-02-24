import { StatusBar } from 'expo-status-bar';
import { StyleSheet, View, Image, Text } from 'react-native';
import { Card, Button, Avatar  } from 'react-native-paper';
import ListaArtefactos from './ListaArtefactos';
import React = require('react');

export const PantallaTodosLosArtefactos = (  ) => {//{ navigation }) => {
  return (
    <View style={styles.container}>
      <View style={styles.card}>
        <Text style={styles.title}>Para conseguir artefactos debes escanear su debido QR en las ubicaciones del mapa!</Text></View>
        <ListaArtefactos></ListaArtefactos>

    </View>
  );
}

export default PantallaTodosLosArtefactos;

const styles = StyleSheet.create({
 
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    //justifyContent: 'center',
    //padding: 15,
    paddingTop: 50, // Para evitar que el icono toque el borde superior
  },
  settingsIcon: {
    position: 'absolute',
    top: 30,
    right: 30,

  },
  title: {
    fontSize: 16,
    fontWeight: '600',
  },
  card: {
    padding: 1,
    width: '100%',
    maxWidth: 375    ,
    backgroundColor: 'white',
    alignItems: 'center',
},
});