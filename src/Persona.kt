

/**
 * Representa a una persona con su peso y altura para calcular el índice de masa corporal (IMC).
 * @property peso El peso de la persona en kilogramos.
 * @property altura La altura de la persona en metros.
 * @property nombre El nombre de la persona.
 * @property imc El índice de masa corporal calculado.
 * @property mediaAltura La media de altura para comparar con la persona.
 * @property mediaPeso La media de peso para comparar con la persona.
 * @constructor Crea una persona con el peso y la altura especificados.
 * @throws IllegalArgumentException Si la altura o el peso son valores no positivos.
 */
class Persona(var peso: Double, var altura: Double) {

    var nombre: String = "Sin Nombre"
    private var imc: Double = 0.0
        get() = calcImc()
        private set(value) {
            field = value
        }

    private var mediaAltura: Double = 1.75

    private var mediaPeso: Double = 70.0

    init {
        require(altura > 0) { "La altura debe ser un valor positivo." }
        require(peso > 0) { "El peso debe ser un valor positivo." }
    }

    /**
     * Crea una persona con el nombre, peso y altura especificados.
     * @param nombre El nombre de la persona.
     * @param peso El peso de la persona en kilogramos.
     * @param altura La altura de la persona en metros.
     * @throws IllegalArgumentException Si el nombre está vacío, la altura o el peso son valores no positivos.
     */
    constructor(nombre: String, peso: Double, altura: Double) : this( peso, altura) {
        require(nombre.isNotEmpty()) {"El nombre no puede estar vacío."}
        this.nombre = nombre
    }

    /**
     * Crea una persona con el nombre, peso, altura, media de peso y media de altura especificados.
     * @param nombre El nombre de la persona.
     * @param peso El peso de la persona en kilogramos.
     * @param altura La altura de la persona en metros.
     * @param mediaPeso La media de peso para comparar con la persona.
     * @param mediaAltura La media de altura para comparar con la persona.
     * @throws IllegalArgumentException Si el nombre está vacío, la altura o el peso son valores no positivos.
     */
    constructor(nombre: String, peso: Double, altura: Double, mediaPeso: Double, mediaAltura: Double) : this(peso, altura) {
        require(nombre.isNotEmpty()) {"El nombre no puede estar vacío."}
        this.nombre = nombre
        this.mediaAltura = mediaAltura
        this.mediaPeso = mediaPeso
    }

    /**
     * Calcula el índice de masa corporal (IMC) de la persona.
     * @return El índice de masa corporal (IMC) calculado.
     */
    private fun calcImc() = peso/(altura*altura)

    /**
     * Devuelve una representación en forma de cadena de la persona.
     * @return La representación en forma de cadena de la persona.
     */
    override fun toString(): String {
        return "nombre: ${this.nombre},  peso: ${this.peso},  altura: ${this.altura}, imc: %.2f".format(imc)
    }

    /**
     * Compara si la instancia actual es igual a otro objeto.
     * @param other El objeto con el que se comparará la instancia actual.
     * @return true si los objetos son iguales, false de lo contrario.
     */
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    /**
     * Saluda a la persona.
     * @return Un saludo con el nombre de la persona.
     */
    fun saludar(): String {
        return "Hola, me llamo $nombre!"
    }

    /**
     * Comprueba si la altura de la persona está por encima de la media.
     * @return true si la altura está por encima de la media, false de lo contrario.
     */
    private fun alturaEncimaMedia():Boolean {
        return (altura >= mediaAltura)
    }

    /**
     * Comprueba si el peso de la persona está por encima de la media.
     * @return true si el peso está por encima de la media, false de lo contrario.
     */
    private fun pesoEncimaMedia():Boolean {
        return (peso >= mediaPeso)
    }

    /**
     * Obtiene la descripción del estado del IMC de la persona.
     * @return La descripción del estado del IMC.
     */
    private fun obtenerDescImc(): String {
        return when {
            imc < 18.5 -> ValoresImc.PESO_INSUFICIENTE.descripcion
            imc in 18.5..24.9 -> ValoresImc.PESO_SALUDABLE.descripcion
            imc in 25.0..29.9 -> ValoresImc.SOBREPESO.descripcion
            else -> ValoresImc.OBESIDAD.descripcion
        }
    }

    /**
     * Obtiene la descripción del estado del cuerpo de la persona según una función proporcionada.
     * @param funcion La función que determina el estado del cuerpo.
     * @return La descripción del estado del cuerpo.
     */
    private fun obtenerDescCuerpo(funcion: () -> Boolean): String {
        return if (funcion()) {
            "Por encima de la media"
        } else {
            "Por debajo de la media"
        }
    }

    /**
     * Obtiene una descripción de la persona con su altura, peso, IMC y estado en comparación con la media.
     * @return Una descripción de la persona.
     */
    fun obtenerDesc():String {
        return "$nombre con una altura de %.2fm (${obtenerDescCuerpo { alturaEncimaMedia() }}) y un peso de %.2fkg (${obtenerDescCuerpo { pesoEncimaMedia() }}) tiene un IMC de %.2f (${obtenerDescImc()})".format(altura, peso, imc)
    }
}