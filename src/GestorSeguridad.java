public class GestorSeguridad {

    public void verificarAcceso(RolUsuario rol, String moduloSistema) {

        System.out.println("Verificando acceso al modulo: " + moduloSistema);

        if(rol.verificarPermisoAcceso(moduloSistema)) {

            System.out.println("Acceso permitido.");

        } else {

            System.out.println("Acceso denegado.");

        }

        System.out.println("-------------------------");
    }
}