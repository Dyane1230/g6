class Ubicacion {
    constructor (
        private id: number,
        private nombre: string,
        private descripcion: string,
        private estado: number = 0,
    ){}
}

export default Ubicacion;