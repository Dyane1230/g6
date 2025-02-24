const apiUrl = process.env.EXPO_PUBLIC_API_URL;

export const GetAllUbicacion = async (  ) => {
    try {
        const response = await fetch(`${apiUrl}/ubicacion`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
            //body: JSON.stringify({ usuari, password })
        });

        if (response.ok || response.status === 201) {
            const jsonData = await response.json();
            return jsonData;
        } else {
            alert("Ubicacion no encontrado");
            return null;
        }

    } catch (error) {
        console.log('Error al obtener los datos:', error);
    }
};