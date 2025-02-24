import { Text, View , StyleSheet, ActivityIndicator, FlatList} from 'react-native';
import { useContext, useEffect, useState } from 'react';
import Artefacto from '../model/Artefacto';
import { GetAllArtefactos } from '../dao/GetAllArtefactos';
import { Card, Button, Avatar  } from 'react-native-paper';




export const ListaArtefactos = (  ) => {//{ navigation }) => {
  const [isLoading, setLoading] = useState(true);
  const [data, setData] = useState<Artefacto[] | null>(null);
  const [error, setError] = useState<string | null>(null);

    async function getArtefactos (){
      const datos = await GetAllArtefactos(); 
      console.log(datos)
      setData(datos);
      setLoading(false);
    }
    useEffect(() => {
        getArtefactos();
      }, []);



    const renderItem = ({ item }: { item: Artefacto }) => (
      <View style={styles.item}>
      <Card style={styles.card}>
        <View style={styles.row}>
          
          <Card.Content>
            <Avatar.Image
              size={65}
              source={{ uri: item.enlace }} />
            <Text style={styles.id}> {item.nombre} </Text>
            <Text style={styles.details}>{item.descripcion} </Text>
          </Card.Content>
          
        </View>
      </Card>
    </View>
    );

      if (isLoading) {
        return (
          <View style={styles.container}>
            <ActivityIndicator />
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


export default ListaArtefactos;

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
      fontSize: 16,
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