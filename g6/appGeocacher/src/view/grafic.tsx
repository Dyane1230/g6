import React from 'react';
import { StyleSheet, ScrollView, View, Text } from 'react-native';
import PieChart from 'react-native-pie-chart';

interface GraficProps {
  value1?: number;
  value2?: number;
}

const Grafic: React.FC<GraficProps> = ({ value1 = 100, value2 = 123 }) => {
  const value1Num = isNaN(value1) ? 0 : value1;
  const value2Num = isNaN(value2) ? 0 : value2;

  if (value1Num + value2Num === 0) {
    return (
      <View style={styles.container}>
        <Text heading>No hay datos para mostrar</Text>
      </View>
    );
  }

  const chart_width = 150;
  const chart_height = 150;

  const series = [
    { value: value1Num, color: '#fbd203' }, // Cada slice tiene un valor y color
    { value: value2Num, color: '#322F99' },
  ];

  return (
    <ScrollView style={{ flex: 1 }}>
      <View style={styles.container}>
        <Text style={styles.id}>Progreso</Text>
        <Text></Text>
        <PieChart
          widthAndHeight={chart_width}
          series={series}
          doughnut={true}
          coverRadius={0.45}
        />
      </View>
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
  },
  id: {
    fontSize: 17,
    fontWeight: 'bold',
  }
});

export default Grafic;