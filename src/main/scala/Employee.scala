import scalafx.beans.property.StringProperty

class Employee(_name: String, _email: String) {
  val name = new StringProperty(_name)
  val email = new StringProperty(_email)
}