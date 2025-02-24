import { useState, useEffect } from 'react';
import { GetAllArtefactos } from '../dao/GetAllArtefactos';
import Artefacto from '../model/Artefacto';

export const useCoordenadasArtefactos = () => {
  const [artefactos, setArtefactos] = useState<Artefacto[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  async function getArtefactos () {
    try {
      const datos = await GetAllArtefactos(); 
      if (datos.length > 0) {
        setArtefactos(datos);
      } else {
        setError("No se encontraron artefactos");
      }
    } catch (err) {
      setError("Error al obtener los artefactos");
      console.error(err);
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    getArtefactos();
  }, []);

  return { artefactos, loading, error };
};