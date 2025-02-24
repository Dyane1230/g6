class Usuario{
    constructor (
        public id: number =0 ,
        public username: string= "username",
        public pass: string = "username",
        public rol: number =0,
        public estado: number =0,
        public nombre: string = "nombre",
        public email: string = "email",
        public foto_perfil: string = "foto",
        public fecha_registro: string = null,
        public biografia: string = null,
        public token: string = "",
    ){}
}

export default Usuario;