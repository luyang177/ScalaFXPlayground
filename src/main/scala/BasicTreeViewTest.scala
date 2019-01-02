import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.collections.ObservableBuffer
import scalafx.geometry.Insets
import scalafx.scene.control._
import scalafx.scene.layout.VBox

object BasicTreeViewTest extends JFXApp {

  stage = new JFXApp.PrimaryStage {
    title = "Tree View Example"
    width = 600
    height = 800
    scene = new Scene {
      stylesheets = List(getClass.getResource("main.css").toExternalForm)
      root = {
        val treeView = new TreeView[String] {
          minWidth = 200
          minHeight = 200
          showRoot = true
          root = new TreeItem[String]("Root Node") {
            expanded = true
            children = ObservableBuffer(
              new TreeItem[String] {
                value = "Node 1"
              },
              new TreeItem[String] {
                value = "Node 2"
              },
              new TreeItem[String] {
                value = "Node 3"
                children = ObservableBuffer(
                  (4 to 12).map(n => new TreeItem[String]("Child Node " + n))
                )
              }
            )
          }
        }

        new VBox {
          padding = Insets(20)
          children = treeView
        }
      }
    }
  }

}
