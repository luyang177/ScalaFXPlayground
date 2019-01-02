
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.geometry.Insets
import scalafx.scene.control._
import scalafx.scene.layout.VBox


object Binding extends JFXApp {

  stage = new JFXApp.PrimaryStage {
    title = "Binding Example"
    scene = new Scene {
      stylesheets = List(getClass.getResource("main.css").toExternalForm)
      root = {
        val employee = new Employee("Initial Name", "a@a.com")
        val editBox = new TextField {
          text <==> employee.name
        }

        val btn1 = new Button {
          text = "Print"
          onMouseClicked = _ => {
            println(employee.name())
          }
        }

        val btn2 = new Button {
          text = "Change"
          onMouseClicked = _ => {
            employee.name() = "Changed Name"
          }
        }

        new VBox {
          padding = Insets(20)
          children = Seq(editBox, btn1, btn2)
        }
      }
    }
  }
}
