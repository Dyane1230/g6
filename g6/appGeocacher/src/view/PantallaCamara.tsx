import { useState, useEffect, useContext } from 'react';
import { View, StyleSheet, ImageBackground } from 'react-native';
import { Camera, CameraView } from 'expo-camera';
import { AuthContext } from '../config/AuthContext';
import { PostUsuarioArtefacto } from '../dao/PostUsuarioArtefacto';
import { GetArtefacto } from '../dao/GetArtefacto';
import Artefacto from '../model/Artefacto';
import { Text } from 'react-native-ui-lib';
import { AlertContainer, Alert } from 'rn-custom-alert-prompt'; 
import ToastManager, { Toast } from 'toastify-react-native';
import React from 'react';

const PantallaCamara = () => {
    const [hasPermission, setHasPermission] = useState(null);
    const [scanned, setScanned] = useState(false);
    const { userId } = useContext(AuthContext);
    const [idArtefactos, setIdArtefactos] = useState(0);
    const [artefacto, setArtefacto] = useState<Artefacto | null>(null);

    async function guardarArtefacto(idUsu: number, idArt: number) {
        console.log("ANTES DE GUARDAR----> ARTEFACTO", idArt);
        console.log("ANTES DE GUARDAR----> USUARIO", idUsu);
        await PostUsuarioArtefacto(idUsu, idArt);
        Toast.success("Artefacto añadido con éxito!");
    }

    async function BuscarArtefacto(idArt: number) {
        console.log("BUSCAR ARTEFACTO ----> ARTEFACTO", idArt);
        const arte = await GetArtefacto(idArt);
        console.log("Datos obtenidos:", arte);

        if (arte) {
            setArtefacto(arte);

            const response = await Alert.alert({
                title: arte.nombre || "Artefacto desconocido",
                description: "¿Quiere añadir este artefacto a su cuenta?",
                showCancelButton: true,
                confirmText: "Añadir",
                cancelText: "Cancelar",
            });

            console.log("Respuesta del usuario:", response); // true o false

            if (response) {
                await guardarArtefacto(userId, idArt);
            }
        } else {
            console.error("Error: El artefacto no se encontró o es null.");
        }
    }

    useEffect(() => {
        const requestPermission = async () => {
            const { status } = await Camera.requestCameraPermissionsAsync();
            setHasPermission(status === 'granted');
        };
        requestPermission();
    }, []);

    const handleBarCodeScanned = ({ type, data }) => {
        setScanned(true);
        const numeroArtefacto = parseInt(data, 10);

        console.log("PANTALLA CAMARA ID Artefacto:", numeroArtefacto);

        if (!isNaN(numeroArtefacto)) {
            setIdArtefactos(numeroArtefacto);
            BuscarArtefacto(numeroArtefacto);
        } else {
            Alert.alert({
                title: "Error",
                description: "El código QR escaneado no es un número válido.",
                showCancelButton: false,
                confirmText: "OK",
            });
        }

        setTimeout(() => setScanned(false), 3000);
    };

    if (hasPermission === null) {
        return <View style={styles.center}><Text body>Solicitando permisos...</Text></View>;
    }
    if (hasPermission === false) {
        return <View style={styles.center}><Text body>No se concedieron permisos para la cámara</Text></View>;
    }

    return (
        <ImageBackground source={require("../image/pokedex.png")} style={styles.background}>
            <View style={styles.container}>
                <ToastManager />
                <AlertContainer />
                <CameraView
                    style={styles.camera}
                    facing="back"
                    onBarcodeScanned={scanned ? undefined : handleBarCodeScanned}
                />
            </View>
        </ImageBackground>
    );
};

const styles = StyleSheet.create({
    background: {
        flex: 1,
        resizeMode: "cover",
        justifyContent: "center",
    },
    container: {
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
    },
    camera: {
        width: 300,
        height: 300,
        borderRadius: 20,
        overflow: "hidden",
    },
    center: {
        flex: 1,
        justifyContent: "center",
        alignItems: "center",
    },
});

export default PantallaCamara;