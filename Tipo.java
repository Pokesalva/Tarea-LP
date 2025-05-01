public enum Tipo{
    AGUA;
    FUEGO;
    PLANTA;
    DRAGON;

    public float getEficacia(Movimiento movAtack, Javaling javaObj) {
        String tipoAtaque = movAtack.getTipo();
        String tipoObjetivo = javaObj.getTipo();

        switch (tipoAtaque) {
            case "AGUA":
                if (tipoObjetivo.equals("FUEGO")) return 2.0f;
                if (tipoObjetivo.equals("PLANTA") || tipoObjetivo.equals("DRAGÓN")) return 0.5f;
                break;
            case "FUEGO":
                if (tipoObjetivo.equals("PLANTA")) return 2.0f;
                if (tipoObjetivo.equals("AGUA") || tipoObjetivo.equals("DRAGÓN")) return 0.5f;
                break;
            case "PLANTA":
                if (tipoObjetivo.equals("AGUA")) return 2.0f;
                if (tipoObjetivo.equals("FUEGO") || tipoObjetivo.equals("DRAGÓN")) return 0.5f;
                break;
            case "DRAGÓN":
                if (tipoObjetivo.equals("DRAGÓN")) return 2.0f;
                break;
            }
        return 1.0f;
    }

}