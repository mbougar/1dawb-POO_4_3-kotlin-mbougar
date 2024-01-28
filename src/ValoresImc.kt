

/**
 * Enumera los diferentes valores de índice de masa corporal (IMC) y sus descripciones asociadas.
 * @property descripcion La descripción asociada al valor del IMC.
 */
enum class ValoresImc(val descripcion: String) {
    PESO_INSUFICIENTE("peso insuficiente"),
    PESO_SALUDABLE("peso saludable"),
    SOBREPESO("sobrepeso"),
    OBESIDAD("obesidad")
}