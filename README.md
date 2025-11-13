# 🃏 Proyecto: Juego de **Siete y Medio** en Java (Swing)

## 🎯 Descripción del proyecto

Este proyecto implementa el clásico juego **Siete y Medio**, utilizando la **baraja española de 40 cartas**.  
Se desarrolla en **Java**, aplicando los contenidos:

- ✔ Clases y objetos  
- ✔ Encapsulamiento  
- ✔ Colecciones dinámicas (`ArrayList`)  
- ✔ Composición entre clases  
- ✔ Métodos primitivos y métodos específicos  
- ✔ Interfaces gráficas con Swing  
- ✔ Eventos (`ActionListener`)  
- ✔ Separación de capas: entre lógica del juego y presentación

---

## ♠️ ♦️ ♥️ ♣️ ¿De qué se trata el juego?

El objetivo del juego **Siete y Medio** es acercarse lo más posible a **7.5 puntos** sin pasarse.

Reglas principales:

- Las cartas del **1 al 7** valen su número.  
- Las cartas **10, 11 y 12** valen **0.5**.  
- Las cartas **8 y 9** no se usan (el mazo queda con 40 cartas).  
- El jugador puede **pedir carta** o **plantarse**.  
- Luego juega la **banca**, que pide cartas hasta superar o igualar al jugador sin pasarse.  
- Gana quien quede más cerca de **7.5**.

---

## 🧩 Componentes del proyecto

El proyecto se basa en varias clases que se relacionan para formar un juego completo:

### 🂡 `Carta`
Representa cada carta (número y palo).  
Incluye el método `obtenerValorParaSieteYMedio()` para calcular su valor real en el juego.  
También genera su ruta de imagen para mostrarse en la GUI.

### 🂱 `Mazo`
Contiene todas las cartas disponibles durante la ronda.  
Permite:
- Armar el mazo de 40 cartas  
- Mezclarlo (`Collections.shuffle`)  
- Repartir cartas (`robarCarta`)  
- Reiniciarlo para una nueva ronda

### 🂩 `Mano`
Representa las cartas que tiene un jugador (o la banca).  
Calcula puntaje y permite verificar si se pasó de **7.5**.

### 🂠 `SieteYMedio`
Es el **motor del juego**.  
Se encarga de:
- Iniciar nuevas rondas  
- Administrar turnos del jugador y de la banca  
- Determinar el ganador  
- Proveer información a la interfaz gráfica

### 🖥️ `FrmPrincipal`
Es la ventana del juego hecha en **Swing**.  
Permite:
- Ver las cartas  
- Pedir carta  
- Plantarse  
- Iniciar nueva ronda  
- Mostrar puntajes y resultados

---

## 🔧 Tecnologías utilizadas

- **Java SE 8+**
- **Swing** para la interfaz gráfica  
- **ArrayList** para colecciones dinámicas  
- `Collections.shuffle` para mezclar el mazo  
- Recursos gráficos en la carpeta `img/`  
  - `img/basto/`
  - `img/copa/`
  - `img/espada/`
  - `img/oro/`

---

## 📦 Estructura del proyecto
src/
 ├── logica/
 │    ├── Carta.java
 │    ├── Mazo.java
 │    ├── Mano.java
 │    └── SieteYMedio.java
 └── grafica/
      └── FrmPrincipal.java

img/
 ├── basto/
 ├── copa/
 ├── espada/
 ├── oro/
 └── otra/tapa.png

---

## 🚀 Cómo ejecutar el proyecto

1. Importar el proyecto en un IDE como **NetBeans**, **Eclipse** o similar, y verificar que Java esté instalado.  
2. Verificar que la carpeta `img/` esté en el directorio raíz del proyecto.  
3. Ejecutar la clase: **FrmPrincipal**.

---

## 👥 Créditos

Proyecto desarrollado por estudiantes de 2do MC como trabajo final la asignatura **Programación Avanzada** del curso **BT - Tecnologías de la Información** perteneciente a **DGETP/CETP - UTU**, bajo modalidad por misiones:

- Misión 1: Valor de las cartas  
- Misión 2: Mazo real  
- Misión 3: Mano del jugador  
- Misión 4: Clase SieteYMedio  
- Misión 5: Integración con GUI

---

## 🃏 ¡Que disfrutes el juego!