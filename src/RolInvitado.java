public class RolInvitado implements RolUsuario {

    @Override
    public boolean verificarPermisoAcceso(String moduloSistema) {

        if(moduloSistema.equalsIgnoreCase("Consulta")) {
            return true;
        }

        return false;
    }
}