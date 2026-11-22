public class RolAdministrador implements RolUsuario {

    @Override
    public boolean verificarPermisoAcceso(String moduloSistema) {

        return true;

    }
}