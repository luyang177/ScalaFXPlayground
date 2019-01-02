
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.collections.ObservableBuffer
import scalafx.geometry.Insets
import scalafx.scene.control._
import javafx.scene.control.cell.TextFieldTreeTableCell
import scalafx.scene.layout.VBox

object ColumnTreeViewTest extends JFXApp {

  stage = new JFXApp.PrimaryStage {
    title = "Tree View Example"
    scene = new Scene {
      stylesheets = List(getClass.getResource("main.css").toExternalForm)
      root = {

        val employees = (1 to 3).map(n => new Employee(s"name_$n", "email")).toBuffer

        val btn1 = new Button {
          text = "print"
          onMouseClicked = _ => {
            println(employees(2).email())
          }
        }

        val btn2 = new Button {
          text = "change"
          onMouseClicked = _ => {
            employees(2).email() = "changed email"
          }
        }

        val columnTreeView = ColumnTreeView(employees)

        new VBox {
          padding = Insets(20)
          children = Seq(columnTreeView, btn1, btn2)
        }
      }
    }
  }
  stage.setMaximized(true)

  def ColumnTreeView(employees : Seq[Employee]) = {
    val root = new TreeItem[Employee](new Employee("Sales Department", ""))
    root.children = ObservableBuffer(
      employees.map(p => new TreeItem[Employee](p)))
    root.setExpanded(true)

    val nameColumn = new TreeTableColumn[Employee, String]("Employee")
    nameColumn.setPrefWidth(450)
    nameColumn.setCellValueFactory(_.getValue.getValue.name)

    val emailColumn = new TreeTableColumn[Employee, String]("Email")
    emailColumn.setPrefWidth(300)
    emailColumn.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn[Employee]())
    emailColumn.setCellValueFactory(_.getValue.getValue.email)

    val treeTableView = new TreeTableView[Employee](root)
    treeTableView.setEditable(true)
    treeTableView.getColumns.setAll(nameColumn, emailColumn)

    treeTableView
  }
}