

# Mapa de colores ANSI para Java
COLORES_ANSI = {
    "rojo":    "\u001B[31m",
    "amarillo":"\u001B[33m",
    "azul":    "\u001B[34m",
    "cian":    "\u001B[36m",
    "verde":   "\u001B[32m",
    "magenta": "\u001B[35m",
    "blanco":  "\u001B[37m",
    "negro":   "\u001B[30m",
    "reset":   "\u001B[0m"
}

def escapar_java(texto):
    texto = texto.replace("\\", "\\\\")
    texto = texto.replace("\"", "\\\"")
    texto = texto.replace("\t", "\\t")
    texto = texto.replace("\b", "\\b")
    texto = texto.replace("\n", "\\n")
    texto = texto.replace("\r", "\\r")
    return texto
resultado = open("colores.txt","w")
def generar_codigo_java(ruta_archivo, color_nombre):
    color = COLORES_ANSI.get(color_nombre.lower(), COLORES_ANSI["reset"])
    reset = COLORES_ANSI["reset"]

    with open(ruta_archivo, 'r', encoding='utf-8') as archivo:
        for linea in archivo:
            linea = linea.rstrip("\n")
            contenido = escapar_java(linea)
            print(f'System.out.println("{color}{contenido}{reset}");')
            resultado.write(f'System.out.println("{color}{contenido}{reset}");\n')


generar_codigo_java("texto.txt", "rojo")
resultado.close()