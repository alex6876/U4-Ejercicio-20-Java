public class RolEditor implements RolUsuario {

    @Override
    public boolean verificarPermisoAcceso(String moduloSistema) {

        if(moduloSistema.equalsIgnoreCase("Edicion")) {
            return true;
        }

        if(moduloSistema.equalsIgnoreCase("Consulta")) {
            return true;
        }

        return false;
    }
}