import scalafx.beans.property.StringProperty

class Employee(_name: String, _email: String) {
  val name: StringProperty = new StringProperty(_name)
  val email: StringProperty = new StringProperty(_email)
}