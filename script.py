import re

def imprimir_tercera_palabra(nombre_archivo):
    with open(nombre_archivo, 'r', encoding='utf-8') as archivo:
        lista = []
        for linea in archivo:
            # Buscar todas las palabras ignorando signos como ; , . ! ?

            palabras = re.findall(r'\b\w+\b', linea)
            if len(palabras) >= 3:
                lista.append(f"{palabras[1]} {palabras[2]},")
                print(f"this.{palabras[2]} = {palabras[2]};")  # Ya no tiene ";" ni otros signos
    for i in lista:
        print(i, end=' ')

# Uso del script
imprimir_tercera_palabra('texto.txt')