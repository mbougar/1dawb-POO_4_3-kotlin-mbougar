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

    constructor(nombre: String, peso: Double, altura: Double) : this( peso, altura) {
        require(nombre.isNotEmpty()) {"El nombre no puede estar vacío."}
        this.nombre = nombre
    }

    constructor(nombre: String, peso: Double, altura: Double, mediaPeso: Double, mediaAltura: Double) : this(peso, altura) {
        require(nombre.isNotEmpty()) {"El nombre no puede estar vacío."}
        this.nombre = nombre
        this.mediaAltura = mediaAltura
        this.mediaPeso = mediaPeso
    }

    private fun calcImc() = peso/(altura*altura)

    override fun toString(): String {
        return "nombre: ${this.nombre},  peso: ${this.peso},  altura: ${this.altura}, imc: %.2f".format(imc)
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    fun saludar(): String {
        return "Hola, me llamo $nombre!"
    }

    private fun alturaEncimaMedia():Boolean {
        return (altura >= mediaAltura)
    }

    private fun pesoEncimaMedia():Boolean {
        return (peso >= mediaPeso)
    }

    private fun obtenerDescImc(): String {
        return when {
            imc < 18.5 -> ValoresImc.PESO_INSUFICIENTE.descripcion
            imc in 18.5..24.9 -> ValoresImc.PESO_SALUDABLE.descripcion
            imc in 25.0..29.9 -> ValoresImc.SOBREPESO.descripcion
            else -> ValoresImc.OBESIDAD.descripcion
        }
    }

    private fun obtenerDescCuerpo(funcion: () -> Boolean): String {
        return if (funcion()) {
            "Por encima de la media"
        } else {
            "Por debajo de la media"
        }
    }

    fun obtenerDesc():String {
        return "$nombre con una altura de %.2fm (${obtenerDescCuerpo { alturaEncimaMedia() }}) y un peso de %.2fkg (${obtenerDescCuerpo { pesoEncimaMedia() }}) tiene un IMC de %.2f (${obtenerDescImc()})".format(altura, peso, imc)
    }
}