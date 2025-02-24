import React, { useState, useRef } from 'react';
import { View, StyleSheet, TextInput, ActivityIndicator, Text } from 'react-native';
import MapView, { Marker } from 'react-native-maps';
import { useCoordenadasArtefactos } from './CoordenadasArtefactos';

const Mapa = () => {
  const [searchQuery, setSearchQuery] = useState('');
  const mapRef = useRef<MapView | null>(null);
  const { artefactos, loading, error } = useCoordenadasArtefactos();

  const handleSearch = () => {
    if (!artefactos) return;
    
    const foundLocation = artefactos.find(artefacto =>
      artefacto.nombre.toLowerCase().includes(searchQuery.toLowerCase())
    );

    if (foundLocation && mapRef.current) {
      mapRef.current.animateToRegion({
        latitude: foundLocation.latitude,
        longitude: foundLocation.longitude,
        latitudeDelta: 0.0001,
        longitudeDelta: 0.0001,
      });
    }
  };

  if (loading) {
    return (
      <View style={styles.loadingContainer}>
        <ActivityIndicator size="large" color="#0000ff" />
      </View>
    );
  }

  if (error) {
    return (
      <View style={styles.errorContainer}>
        <Text style={styles.errorText}>{error}</Text>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <TextInput
        style={styles.searchInput}
        placeholder="Buscar artefacto..."
        value={searchQuery}
        onChangeText={setSearchQuery}
        onSubmitEditing={handleSearch}
      />
      <MapView
        ref={mapRef}
        style={styles.map}
        initialRegion={{
          latitude: 41.3888,
          longitude: 2.1479,
          latitudeDelta: 0.04,
          longitudeDelta: 0.05,
        }}
        >
        {artefactos &&
          artefactos
            .filter(a => a.latitude !== 0 && a.longitude !== 0) 
            .map(artefacto => (
              <Marker
                key={artefacto.id}
                coordinate={{ latitude: artefacto.latitude, longitude: artefacto.longitude }}
                title={artefacto.nombre}
                description={artefacto.descripcion}
              />
            ))}
        </MapView>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  searchInput: {
    position: 'absolute',
    top: 40,
    left: 20,
    right: 20,
    backgroundColor: 'white',
    padding: 10,
    borderRadius: 8,
    zIndex: 1,
    elevation: 3,
  },
  map: {
    width: '100%',
    height: '100%',
  },
  loadingContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  errorContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  errorText: {
    color: 'red',
    fontSize: 16,
  },
});

export default Mapa;