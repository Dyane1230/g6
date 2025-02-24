import { Text, View , StyleSheet, ActivityIndicator, FlatList} from 'react-native';
import { useContext, useEffect, useState } from 'react';
import Artefacto from '../model/Artefacto';
import { GetArtefactoDeUsuario }from '../dao/GetArtefactoDeUsuario';
import { Card, Button, Avatar  } from 'react-native-paper';
import { AuthContext } from '../config/AuthContext';

export const PantallaArtefacto = (  ) => {//{ navigation }) => {
  const [isLoading, setLoading] = useState(true);
  const [data, setData] = useState<Artefacto[] | null>(null);
  const [error, setError] = useState<string | null>(null);
  const { isLoggedIn, setLoggedIn, setUserId, userId } = useContext(AuthContext);



    async function getArtefactos (){
      const datos = await GetArtefactoDeUsuario(userId, ''); //cambiar a una variable de id_usuario
      setTimeout(() => {
        setData(datos);
        setLoading(false);
      }, 5000); // Espera 5 segundos antes de mostrar los datos
    }
    useEffect(() => {
      getArtefactos();
    }, []);



    const renderItem = ({ item }: { item: Artefacto }) => (
      <View style={styles.item}>
      <Card style={styles.card}>
        <View style={styles.row}>
          <Card.Content>
            <Text style={styles.id}> {item.nombre} </Text>
            <Text style={styles.details}> ID: {item.id} </Text>
          </Card.Content>
          <Avatar.Image
            size={65}
            source={{ uri: item.enlace }} />
        </View>
      </Card>
    </View>
    );

      if (isLoading) {
        return (
          <View style={styles.container}>
            <ActivityIndicator size="large" />
          </View>
        );
      }

      if (error) {
        return (
          <View style={styles.container}>
            <Text style={styles.error}>{error}</Text>
          </View>
        );
      }

      if (data) {
        return (
          <View style={{ flex: 1, marginTop: 20, padding: 24 }}>
            <FlatList
              data={data && data?.length>0 && data}
              renderItem={renderItem}
              keyExtractor={item => item.id.toString()}
            />
          </View>
        );
      }
      return null;
    }


export default PantallaArtefacto;

const styles = StyleSheet.create({
    container: {
      flex: 1,
      backgroundColor: '#fff',
      alignItems: 'center',
      justifyContent: 'center',
    },
    item: {
      marginBottom: 10,
      padding: 10,
      //borderWidth: 1,
      //borderColor: '#ddd',
      borderRadius: 10,
      //alignItems: 'center',
    },
    image: {
      width: 100,
      height: 100,
    },
    id: {
      fontSize: 17,
      fontWeight: 'bold',
    },
    details: {
      fontSize: 20,
      color: '#555',
    },
    error: {
      color: 'red',
      fontSize: 18,
      textAlign: 'center',
    },
    row: {
      flexDirection: 'row',  // Alinea los elementos horizontalmente
      alignItems: 'center',   // Centra verticalmente
      justifyContent: 'space-between', // Separa los elementos
    },
    card: {
      width: '100%',
      padding: 10,
    },
  });