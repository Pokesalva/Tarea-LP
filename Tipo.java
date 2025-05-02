public enum Tipo {
    PLANTA,
    FUEGO,
    AGUA,
    DRAGON;
    
    public float getEficacia(Tipo tipoObjetivo) {
        switch (this) {
            case AGUA:
                if (tipoObjetivo == FUEGO) return 2.0f;
                if (tipoObjetivo == PLANTA || tipoObjetivo == DRAGON) return 0.5f;
                return 1.0f;
            case FUEGO:
                if (tipoObjetivo == PLANTA) return 2.0f;
                if (tipoObjetivo == AGUA || tipoObjetivo == DRAGON) return 0.5f;
                return 1.0f;
            case PLANTA:
                if (tipoObjetivo == AGUA) return 2.0f;
                if (tipoObjetivo == FUEGO || tipoObjetivo == DRAGON) return 0.5f;
                return 1.0f;
            case DRAGON:
                return 1.0f; // Puedes ajustar la eficacia para el tipo DRAGON según tus necesidades
            default:
                return 1.0f;
        }
    }
}