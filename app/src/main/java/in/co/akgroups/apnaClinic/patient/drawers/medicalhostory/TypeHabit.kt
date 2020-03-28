package `in`.co.akgroups.apnaClinic.patient.drawers.medicalhostory

class TypeHabit {

    var isSelected = false

    var id : Long = 0L
    var name : String = ""

    override fun equals(other: Any?): Boolean {
        if (other is TypeHabit) {
            return this.name == other.name
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        val prime = 59
        var result = 1
        result = result * prime + if (this.id == null) 43 else this.id.hashCode()
        result = result * prime + if (this.name == null) 43 else this.name.hashCode()
        return result
    }
}