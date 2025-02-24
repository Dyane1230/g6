const apiUrl = process.env.EXPO_PUBLIC_API_URL;

export const GetArtefacto = async ( id_artefacto: number ) => {
    try {
        const response = await fetch(`${apiUrl}/artefacto/${id_artefacto}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
            //body: JSON.stringify({ id_usuario, text })
        });

        if (response.ok || response.status === 201) {
            const jsonData = await response.json();
            return jsonData;
        } else {
            alert("Artefacto no encontrado");
            return null;
        }

    } catch (error) {
        console.log('Error al obtener los datos:', error);
    }
};