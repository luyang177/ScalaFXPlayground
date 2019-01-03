import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.beans.property.{ObjectProperty, StringProperty}
import scalafx.collections.ObservableBuffer
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.TableColumn._
import scalafx.scene.control.{Button, TableCell, TableColumn, TableView}
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color
import scalafx.scene.shape.Circle

class Person(firstName_ : String,
             lastName_ : String,
             favoriteColor_ : Color) {
  val firstName = new StringProperty(this, "firstName", firstName_)
  val lastName = new StringProperty(this, "lastName", lastName_)
  val favoriteColor = new ObjectProperty(this, "favoriteColor",
    favoriteColor_)
}

object TableWithCustomCellDemo extends JFXApp {

  val characters = ObservableBuffer[Person](
    new Person("Peggy", "Sue", Color.Violet),
    new Person("Rocky", "Raccoon", Color.GreenYellow),
    new Person("Bungalow ", "Bill", Color.DarkSalmon)
  )

  stage = new PrimaryStage {
    title = "TableView with custom color cell"
    scene = new Scene {
      stylesheets = List(getClass.getResource("simple.css").toExternalForm)
      root = {
        val tableView = new TableView[Person](characters) {
          columns ++= List(
            new TableColumn[Person, String] {
              text = "First Name"
              cellValueFactory = { _.value.firstName }
              prefWidth = 200
            },
            new TableColumn[Person, String]() {
              text = "Last Name"
              cellValueFactory = { _.value.lastName }
              prefWidth = 200
            },
            new TableColumn[Person, Color] {
              text = "Favorite Color"
              cellValueFactory = { _.value.favoriteColor }
              // Render the property value when it changes,
              // including initial assignment
              cellFactory = { _ =>
                new TableCell[Person, Color] {
                  item.onChange { (_, _, newColor) =>
                    graphic =
                      if (newColor != null)
                        new Circle {
                          fill = newColor
                          radius = 8
                        }
                      else
                        null
                  }
                }
              }
              prefWidth = 200
            }
          )
        }

        val btnAdd = new Button {
          text = "Add"
          onMouseClicked = _ => {
            characters += new Person("new name", "new name", Color.Violet)
          }
        }

        val btnRemove = new Button {
          text = "Remove First"
          onMouseClicked = _ => {
            characters.remove(0)
          }
        }

        val btnEdit = new Button {
          text = "Edit First"
          onMouseClicked = _ => {
            characters(0).firstName() = "Changed Name"
          }
        }

        new VBox {
          padding = Insets(20)
          children = Seq(tableView, btnAdd, btnRemove, btnEdit)
        }

      }
    }
  }
}