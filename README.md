Programacion Funcional y Reactiva
Mini Proyecto: Higher Order Functions en
Criptografıa
------------------------------------------

Descripcion del Trabajo

El objetivo es crear un motor de cifrado que reciba una funcion de transformacion
como parametro. Se deben implementar dos metodos de cifrado especıficos:
1. Cifrado Cesar (Desplazamiento):
Consiste en mover cada letra del alfabeto un numero fijo de posiciones.

E(x, k) = (pos(x) + k) mod 26

Donde x es el caracter y k es la clave (entero).
3. Cifrado XOR (Logico):
Aplica la operacion Exclusive OR a nivel de bits entre el codigo ASCII del caracter
y una clave numerica.

E(x, k) = ASCII(x) ⊕ k

Nota de implementacion: Para realizar esta operacion a nivel de bits en el
codigo, se debe utilizar el operador ^ (caret). Este metodo tiene la propiedad de
que aplicar la misma operacion dos veces devuelve el valor original.
Tambien se debe implementar un manejo adecuado de excepciones, tales como:

• Cadenas de texto vacıas o nulas.

• Claves invalidas.

• Caracteres no soportados.

1. Implementacion de Funciones
   -

   <img width="729" height="184" alt="image" src="https://github.com/user-attachments/assets/46e892ee-281c-49d6-9f7c-980a386dd218" />

   <img width="695" height="406" alt="image" src="https://github.com/user-attachments/assets/8baf1804-d006-4020-a912-d27fbf5baefa" />



-------------------
Pruebas de Cifrado y Descifrado 
---------------------

Ejemplo 1: Metodo Cesar
-
Cifrar la palabra ”FUNCIONAL” con clave k = 3.

• Descifrar el resultado obtenido (usando clave −3 o logica inversa) para recuperar el mensaje original.

<img width="678" height="63" alt="image" src="https://github.com/user-attachments/assets/1c78306c-0625-4037-a813-c1f976e85a90" />

<img width="426" height="49" alt="image" src="https://github.com/user-attachments/assets/045f0167-8fef-42ed-aef6-a6afd4a84b0d" />


Ejemplo 2: Metodo Cesar
-
Para el mensaje ”AD” con clave k = 1:

• Convertir caracteres a Indices (A=0, B=1, ...).

• Letra ’A’ (0): (0 + 1) mod 26 = 1 → ’B’

• Letra ’D’ (3): (3 + 1) mod 26 = 4 → ’E’

• Resultado: ”BE”

<img width="499" height="49" alt="image" src="https://github.com/user-attachments/assets/ade8c464-eed0-469e-a326-fc4fe36e94eb" />


<img width="384" height="34" alt="image" src="https://github.com/user-attachments/assets/7f77b8d1-c4c9-4c51-9680-81c804522557" />


Ejemplo 3: Metodo XOR
-
Para el mensaje ”scala” con clave k = 5:

Paso detallado para la primera letra ’s’:

• Codigo ASCII de ’s’: 115.

• En codigo Scala: 115 ^ 5.

• Representacion binaria ’s’: 0111 0011

• Representacion binaria 5: 0000 0101

• Operacion XOR (⊕): 0111 0110 (Decimal 118)

• Caracter ASCII 118: ’v’

<img width="608" height="97" alt="image" src="https://github.com/user-attachments/assets/faade389-40bc-4a0c-9315-ee33d25a7fc1" />

<img width="594" height="96" alt="image" src="https://github.com/user-attachments/assets/da4502bf-6927-4a4a-9042-5848c39f680d" />

Manejo de errores
-
<img width="536" height="161" alt="image" src="https://github.com/user-attachments/assets/7611b333-d58f-4644-aab5-c4dd8fe43284" />

<img width="939" height="161" alt="image" src="https://github.com/user-attachments/assets/0dcd6ba9-ddad-4fc2-931a-dd32032b8e73" />


-----------------------------
Preguntas para Reflexionar
-------------------------------
• ¿Por que el metodo XOR es su propia funcion inversa utilizando el mismo operador ^, mientras que el Cesar requiere invertir el signo de la clave?

El XOR funciona así debido a una propiedad matemática de involución. Si le aplicas el operador ^ con la misma clave dos veces seguidas al valor (A ^ k ^ k), el resultado regresa al valor original. En cambio, el cifrado César se desplaza a lo largo de un ciclo cerrado (el alfabeto de 26 letras), por lo que para deshacer ese desplazamiento no puedo seguir avanzando en la misma dirección; necesito invertir el signo de la clave (restando en lugar de sumar) para volver atras

• ¿Que ventajas ofrece el uso de map sobre un bucle for tradicional en este contexto?

Usar map me permitió escribir un código mucho más limpio, sencillo y funcional, enfocándome en qué quería transformar en lugar de controlar manualmente los pasos de un bucle. Esto evitó que tuviera que usar variables auxiliares mutables o contadores, manteniendo la inmutabilidad de los datos y reduciendo los típicos errores con el manejo de índices

• ¿Como ayuda el tipado estatico a prevenir errores al pasar funciones como argumentos?

El tipado estático me sirvió como una red de seguridad desde el momento de compilar. Al definir explícitamente la firma exacta de la función que esperaba recibir (como (Char, Int) => Char), el compilador se encarga de revisar que cualquier función que le mande cumpla exactamente con esos parámetros. Si por descuido hubiera intentado pasarle una función incompatible (que recibiera un tipo de dato diferente), el compilador me lo habría bloqueado de inmediato antes de ejecutar el programa
