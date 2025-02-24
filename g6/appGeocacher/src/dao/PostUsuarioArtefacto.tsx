const apiUrl = process.env.EXPO_PUBLIC_API_URL;

export const PostUsuarioArtefacto = async ( idusuario: number, idArtefacto: number  ) => {
    try {
        const response = await fetch(`${apiUrl}/usuario/${idusuario}/artefacto/${idArtefacto}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            //body: JSON.stringify({ usuari, password })
        });

        if (response.ok || response.status === 201) {
            const jsonData = await response.json();
            return null;
        } else {
            alert("Artefactos no encontrado");
            return null;
        }

    } catch (error) {
        console.log('Error al obtener los datos:', error);
    }
};