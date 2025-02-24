import { Double, Float } from "react-native/Libraries/Types/CodegenTypes";
import Ubicacion from "./Ubicacion";

class Artefacto {
    constructor (
        public id: number = 0,
        public descripcion: string = "descripción",
        public nombre: string = "user",
        public id_ubicacion: Ubicacion,
        public estado: number = 0,
        public latitude: number = 0.0,
        public longitude: number = 0.0,
        public enlace: string = "enlace"
    ) {}
}

export default Artefacto;