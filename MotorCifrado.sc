import scala.util.Try

  // 1. Funciones de cifrado


  // Cifrado César
  val cifradoCesar: (Char, Int) => Char = (c, k) => {
    if (c >= 'A' && c <= 'Z')
      ('A' + Math.floorMod(c - 'A' + k, 26)).toChar
    else if (c >= 'a' && c <= 'z')
      ('a' + Math.floorMod(c - 'a' + k, 26)).toChar
    else
      throw new IllegalArgumentException("Caracter no soportado en César: " + c)
  }

  // Cifrado XOR
  val cifradoXor: (Char, Int) => Char = (c, k) => {
    if (c.toInt > 127)
      throw new IllegalArgumentException("Caracter no ASCII: " + c)
    if (k < 0 || k > 127)
      throw new IllegalArgumentException("Clave XOR inválida (0 a 127): " + k)
    (c.toInt ^ k).toChar
  }

  // 2- Función de orden superior



  def procesarMensaje(mensaje: String, clave: Int, cifrado: (Char, Int) => Char): Try[String] =
    Try {
      if (mensaje == null || mensaje.isEmpty)
        throw new IllegalArgumentException("El mensaje no puede ser nulo ni vacío")
      if (clave < -255 || clave > 255)
        throw new IllegalArgumentException("Clave fuera de rango (-255 a 255): " + clave)
      mensaje.map(c => cifrado(c, clave))
    }

  // 3- Pruebas

  // Caso César
  val cesarCifrado    = procesarMensaje("FUNCIONAL", 3, cifradoCesar)                 // Success(IXQFLRQDO)
  val cesarDescifrado = cesarCifrado.flatMap(t => procesarMensaje(t, -3, cifradoCesar)) // Success(FUNCIONAL)

  val cesarEjemplo    = procesarMensaje("AD", 1, cifradoCesar)                         // Success(BE)

  // Caso XOR
  val xorLambda: (Char, Int) => Char = (c, k) => (c.toInt ^ k).toChar
  val xorCifrado    = procesarMensaje("scala", 5, xorLambda)                          // Success(vfdid)
  val xorDescifrado = xorCifrado.flatMap(t => procesarMensaje(t, 5, xorLambda))       // Success(scala)

  // Manejo de errores
  val errorVacio    = procesarMensaje("", 3, cifradoCesar)
  val errorNulo     = procesarMensaje(null, 3, cifradoCesar)
  val errorClave    = procesarMensaje("HOLA", 100000, cifradoCesar)
  val errorEspecial = procesarMensaje("HOLA!", 3, cifradoCesar)
  val errorXorClave = procesarMensaje("scala", -5, cifradoXor)
  val errorNoAscii  = procesarMensaje("ñandú", 5, cifradoXor)
