# Proyecto de Pruebas Automatizadas - Nivo App

Este proyecto de automatización con appium para interactuar con la aplicación de Nivo en Android. El objetivo es verificar ciertos flujos en la app de Nivo con un dispositivo real.

## Tecnologías Utilizadas

- Java: lenguaje principal del proyecto.
- JUnit 4: Framework de pruebas unitarias.
- Appium: Framework para la automatización de aplicaciones móviles.
- Maven: herramienta de construcción y gestión de dependencias.

## Configuración del Proyecto

### Dependencias Principales (Maven)

El proyecto utiliza varias dependencias necesarias para ejecutar las pruebas automatizadas. A continuación, se detallan las dependencias usadas en el archivo `pom.xml`:

```xml
<dependencies>
    <!-- Appium Java Client -->
    <dependency>
        <groupId>io.appium</groupId>
        <artifactId>java-client</artifactId>
        <version>8.2.0</version>
    </dependency>

    <!-- JUnit 4 -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.13.2</version>
        <scope>test</scope>
    </dependency>

    <!-- Selenium Remote Driver -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-remote-driver</artifactId>
        <version>3.141.59</version>
    </dependency>
</dependencies>

Configuración de Appium
Para ejecutar las pruebas en un dispositivo Android, se debe tener Appium instalado y configurado detalles de configuracion (Server Address: localhost, Server Port: 4750, check: Allow CORS, check: Relaxed Security), configuracion appium inspector (Server Address: localhost, Server Port: 4750, Remote Path: /wd/hub, Advanced Settings: check: Allow Unauthorized Certificates, JSON Representation:

{
  "platformName": "Android",
  "appium:deviceName": "consultar con adb devices",
  "appium:appPackage": "com.meridiangroupsa.nivo",
  "appium:appActivity": "com.meridiangroupsa.nivo.MainActivity",
  "appium:noReset": true,
  "appium:autoLaunch": false,
  "appium:fullReset": false,
  "appium:newCommandTimeout": 300,
  "appium:dontStopAppOnReset": true,
  "appium:appWaitActivity": "com.meridiangroupsa.nivo.*"
}
).

appium -p 4750 (lo puedes ajustar a cualquier otro puerto si lo tiene ocupado)

Ejecución de las pruebas

mvn -Dtest=TestSuite test

Estructura del Proyecto
src/test/java/com/nivo/tests/NivoOpenAppTest.java: Contiene la prueba que abre la aplicación de Nivo en un dispositivo Android.

pom.xml: Contiene todas las dependencias y configuraciones de Maven.

Requisitos
Java JDK 11+: Asegúrate de tener Java correctamente configurado.
Maven: Para gestionar las dependencias y ejecutar las pruebas.
Appium: Debes instalar Appium globalmente en tu sistema.
Dispositivo Android o Emulador: Asegúrate de tener el dispositivo conectado y depuración USB habilitada.

Autor
Nombre del autor: Alejandra Nuñez
