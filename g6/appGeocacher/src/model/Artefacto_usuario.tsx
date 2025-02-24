import Artefacto from "./Artefacto";
import Usuario from "./Usuario";

class Artefacto_Usuario{
    constructor(
        private id: number,
        private usuario: Usuario,
        private artefacto: Artefacto,
        private fecha_descubrimiento: string,
    ){}
}

export default Artefacto_Usuario;