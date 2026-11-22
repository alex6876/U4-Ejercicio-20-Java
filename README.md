# Simulación de Control de Acceso Basado en Roles y Gestor de Seguridad (Interfaces y Polimorfismo en Java)

Este proyecto es una aplicación en Java que modela un sistema de autorización y control de acceso por roles (RBAC), implementando una arquitectura basada en **interfaces (`interface`)** para desacoplar el gestor de seguridad de las distintas políticas de permisos (`RolAdministrador`, `RolEditor` y `RolInvitado`) aplicadas sobre los módulos del sistema. Pone en práctica conceptos fundamentales de la Programación Orientada a Objetos (POO) como la definición de contratos obligatorios mediante interfaces, la sobrescritura polimórfica de comportamiento (`@Override`), la inyección de dependencias por parámetros y el procesamiento desacoplado en memoria sin persistencia en base de datos.

---

## Clases y Estructura del Sistema

El diseño del software se organiza a través de una interfaz de contrato, sus respectivas clases implementadoras por rol de usuario, un componente gestor de seguridad y un punto de ejecución:

* **`RolUsuario` (Interfaz)**: Define el contrato estándar que cualquier rol del sistema debe cumplir obligatoriamente para evaluar permisos.
* **Método abstracto**: `boolean verificarPermisoAcceso(String moduloSistema)`: Obliga a las clases implementadoras a determinar si el rol tiene autorización para operar sobre el módulo indicado, retornando `true` o `false`.


* **`RolAdministrador`**: Implementa la interfaz `RolUsuario` para modelar el perfil con privilegios totales dentro de la aplicación.
* **Sobrescritura (`@Override`)**:
* `verificarPermisoAcceso(String moduloSistema)`: Retorna `true` para cualquier módulo solicitado, habilitando el acceso irrestricto.




* **`RolEditor`**: Implementa `RolUsuario` para modelar el perfil con permisos restringidos a tareas de edición y lectura.
* **Sobrescritura (`@Override`)**:
* `verificarPermisoAcceso(String moduloSistema)`: Permite el acceso (`true`) únicamente si el módulo coincide (sin distinguir mayúsculas o minúsculas) con `"Edicion"` o `"Consulta"`, denegándolo (`false`) en cualquier otro caso.




* **`RolInvitado`**: Implementa `RolUsuario` para modelar el perfil de solo lectura con el nivel más restrictivo.
* **Sobrescritura (`@Override`)**:
* `verificarPermisoAcceso(String moduloSistema)`: Autoriza (`true`) únicamente el acceso al módulo `"Consulta"` (ignorando mayúsculas/minúsculas), devolviendo `false` para el resto de los módulos.




* **`GestorSeguridad`**: Componente controlador encargado de ejecutar la comprobación de permisos y reportar el resultado por consola.
* **Método operativo**: `verificarAcceso(RolUsuario rol, String moduloSistema)`: Recibe polimórficamente cualquier objeto que implemente `RolUsuario`, informa el módulo evaluado, invoca `rol.verificarPermisoAcceso(moduloSistema)` e imprime `"Acceso permitido."` o `"Acceso denegado."`.


* **`Main`**: Punto de entrada del programa. Instancia `GestorSeguridad` y los roles `RolAdministrador`, `RolEditor` y `RolInvitado`, evaluando cada rol frente a los módulos `"Administracion"`, `"Edicion"` y `"Consulta"`.

---

## Reglas de Negocio y Control de Validación

* **Contrato Estricto de Autorización:** Mediante la interfaz `RolUsuario`, cualquier nuevo rol que se incorpore al sistema (por ejemplo, `RolAuditor` o `RolSupervisor`) debe implementar obligatoriamente `verificarPermisoAcceso(String moduloSistema)`.
* **Políticas Diferenciadas por Rol:** Cada clase implementadora encapsula su propia matriz de permisos (`RolAdministrador` habilita todo; `RolEditor` habilita `"Edicion"` y `"Consulta"`; `RolInvitado` habilita solo `"Consulta"`), evitando condicionales centralizados en el gestor.
* **Desacoplamiento mediante Polimorfismo:** `GestorSeguridad` opera exclusivamente sobre la abstracción `RolUsuario`, resolviendo en tiempo de ejecución la regla de acceso correspondiente a cada rol sin depender de sus clases concretas.

---

## Salida por Consola

Al ejecutar la clase `Main`, el programa genera el siguiente resultado:

```text
=== ADMINISTRADOR ===
Verificando acceso al modulo: Administracion
Acceso permitido.
-------------------------
Verificando acceso al modulo: Edicion
Acceso permitido.
-------------------------
Verificando acceso al modulo: Consulta
Acceso permitido.
-------------------------
=== EDITOR ===
Verificando acceso al modulo: Administracion
Acceso denegado.
-------------------------
Verificando acceso al modulo: Edicion
Acceso permitido.
-------------------------
Verificando acceso al modulo: Consulta
Acceso permitido.
-------------------------
=== INVITADO ===
Verificando acceso al modulo: Administracion
Acceso denegado.
-------------------------
Verificando acceso al modulo: Edicion
Acceso denegado.
-------------------------
Verificando acceso al modulo: Consulta
Acceso permitido.
-------------------------

```

---

## Conceptos de Programación Aplicados

* **Polimorfismo de Interfaces:** Uso de un contrato común (`interface`) para evaluar permisos heterogéneos bajo una misma referencia tipada.
* **Principio de Inversión de Dependencias:** El componente de control (`GestorSeguridad`) depende de una abstracción en lugar de depender de implementaciones concretas.
* **Separación de Responsabilidades:** División clara entre las reglas de autorización de cada rol, la verificación centralizada en `GestorSeguridad` y la ejecución de escenarios de prueba en `Main`.
