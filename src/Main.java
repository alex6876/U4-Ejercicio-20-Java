public class Main {

    public static void main(String[] args) {

        GestorSeguridad gestor = new GestorSeguridad();

        RolUsuario administrador = new RolAdministrador();
        RolUsuario editor = new RolEditor();
        RolUsuario invitado = new RolInvitado();


        System.out.println("=== ADMINISTRADOR ===");

        gestor.verificarAcceso(administrador, "Administracion");
        gestor.verificarAcceso(administrador, "Edicion");
        gestor.verificarAcceso(administrador, "Consulta");


        System.out.println("=== EDITOR ===");

        gestor.verificarAcceso(editor, "Administracion");
        gestor.verificarAcceso(editor, "Edicion");
        gestor.verificarAcceso(editor, "Consulta");


        System.out.println("=== INVITADO ===");

        gestor.verificarAcceso(invitado, "Administracion");
        gestor.verificarAcceso(invitado, "Edicion");
        gestor.verificarAcceso(invitado, "Consulta");

    }
}