# 🎟️ Sistema de Boletería y Gestión de Tickets (Java)

¡Bienvenido al **Sistema de Boletería y Gestión de Tickets**! Este proyecto es una aplicación de consola robusta desarrollada en Java, diseñada para gestionar la venta de asientos para eventos, persisitir datos en múltiples formatos (CSV y JSON), emitir tiquetes digitales en PDF con código QR dinámico y generar reportes estadísticos visuales.

---

## 🚀 Características Principales

- **Lectura y Validación de Datos (CSV):**
    - Carga masiva de eventos y asientos desde archivos CSV (`eventos.csv` y `asientos.csv`).
    - Validación estricta de formato numérico (`NumberFormatException`) para mitigar la lectura de datos corruptos.

- **Procesamiento de Compras y Negocio:**
    - Filtrado de asientos por evento y estado de disponibilidad.
    - Validación de campos requeridos (Documento, Nombre, Correo).
    - Cálculo automático de desgloses de costo (Subtotal, Cargo por Servicio del 10% y Total).
    - Actualización del estado de disponibilidad de los asientos comprados en memoria.

- **Persistencia en JSON (Jackson 3.x):**
    - Almacenamiento e historial acumulativo de las transacciones en `compras.json` mediante la librería Jackson.

- **Generación de Tiquetes en PDF y Códigos QR:**
    - Generación automática de un recibo o ticket digital PDF para cada compra usando **Apache PDFBox 3.0.2**.
    - Generación de código QR dinámico con la información de la compra usando **ZXing 3.5.3**, el cual se incrusta automáticamente en el PDF.

- **Módulo de Reportes Estadísticos:**
    - Generación de gráficos de barras mediante **JFreeChart 1.5.4** para la recaudación de ganancias ($) y cantidad de boletos vendidos por evento.
    - Consolidación de gráficos e imágenes estadísticos en un reporte PDF final (`Reporte_Consolidado_Ventas.pdf`).

- **Auditoría y Logs:**
    - Registro cronológico detallado de eventos, compras y errores con marca de tiempo (`yyyy-MM-dd HH:mm:ss`) en `auditoria.txt`.

- **Pruebas Unitarias:**
    - Cobertura de la lógica de negocio mediante suite de pruebas aisladas con **JUnit 5**.

---

## 🛠️ Tecnologías y Librerías Utilizadas

| Componente / Librería | Versión | Descripción / Uso |
| :--- | :--- | :--- |
| **Java JDK** | 17+ | Lenguaje de programación principal |
| **Apache PDFBox** | 3.0.2 | Generación y maquetación de tiquetes y reportes PDF |
| **ZXing (Zebra Crossing)** | 3.5.3 | Generación de matrices y códigos QR en formato PNG |
| **Jackson** | 3.x | Mapeo y serialización de objetos Java a JSON (`compras.json`) |
| **JFreeChart** | 1.5.4 | Creación de gráficos estadísticos de barras |
| **JUnit** | 5.x | Pruebas unitarias automatizadas |

---

## 📋 Requisitos Previos

- **Java Development Kit (JDK):** Versión 17 o superior instalada.
- **Apache Maven:** Para la gestión de dependencias y construcción del proyecto.

---

## 🔧 Configuración e Instalación

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/Caadaavid/LabBoleteria.git
   cd LabBoleteria
   ```

2. **Compilar y descargar dependencias:**
   ```bash
   mvn clean compile
   ```

3. **Ejecutar las pruebas unitarias:**
   ```bash
   mvn test
   ```

4. **Ejecutar la aplicación principal:**
   ```bash
   mvn exec:java -Dexec.mainClass="Main"
   ```

---

## 💻 Uso de la Aplicación

Al iniciar la aplicación mediante la clase `Main`, se desplegará el menú interactivo en consola:

```text
=== MENÚ DEL SISTEMA ===
1. Mostrar asientos de algún evento
2. Comprar asientos
3. Generar reportes gráficos de ganancias
0. Salir
Seleccionar opción: 
```

1. **Mostrar asientos:** Ingresa el ID de un evento (ej. `EV001`) para filtrar y visualizar el listado de asientos y su disponibilidad.
2. **Comprar asientos:** Solicita los datos del cliente, la cantidad de boletos y sus respectivos IDs. Al completarse la compra:
    - Se guardará la transacción en `src/data/compras.json`.
    - Se creará una imagen QR en `src/qr/`.
    - Se generará el tiquete en PDF en `src/tickets/`.
    - Se registrará el evento en `src/logs/auditoria.txt`.
3. **Generar reportes gráficos:** Procesa las ventas acumuladas en la sesión y genera los gráficos `.png` junto con el reporte consolidado `Reporte_Consolidado_Ventas.pdf` en `src/reportes/`.

---

## 📝 Licencia

Este proyecto fue desarrollado con fines educativos y de aprendizaje práctico.