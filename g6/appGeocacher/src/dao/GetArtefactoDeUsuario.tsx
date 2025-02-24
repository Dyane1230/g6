const apiUrl = process.env.EXPO_PUBLIC_API_URL;

export const GetArtefactoDeUsuario = async ( id_usuario: number, text:string ) => {
    try {
        const response = await fetch(`${apiUrl}/usuario/${id_usuario}/artefacto/search?text=${text}`, {
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
            alert("Usuario no encontrado");
            return null;
        }

    } catch (error) {
        console.log('Error al obtener los datos:', error);
    }
};