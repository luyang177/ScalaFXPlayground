
import scalafx.application.JFXApp
import scalafx.scene.Scene
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.control._
import scalafx.scene.layout.{GridPane, HBox}
import scalafx.scene.text.{Font, FontWeight, Text}
import scalafx.scene.paint.Color


object GridPaneTest extends JFXApp {

  stage = new JFXApp.PrimaryStage {
    title = "GridPane Example"
    width = 500
    scene = new Scene {
      stylesheets = List(getClass.getResource("simple.css").toExternalForm)
      root = {
        val grid = new GridPane
        grid.setAlignment(Pos.Center)
        grid.setHgap(10)
        grid.setVgap(10)
        grid.setPadding(Insets(25))

        val sceneTitle = new Text("Welcome")
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.Normal, 20))
        grid.add(sceneTitle, 0, 0, 2, 1)

        val userName = new Label("User Name:")
        grid.add(userName, 0, 1)

        val userTextField = new TextField
        grid.add(userTextField, 1, 1)

        val pw = new Label("Password:")
        grid.add(pw, 0, 2)

        val pwBox = new PasswordField
        grid.add(pwBox, 1, 2)

        val btn = new Button("Sign in")
        val hbBtn = new HBox(10)
        hbBtn.setAlignment(Pos.BottomRight)
        hbBtn.getChildren.add(btn)
        grid.add(hbBtn, 1, 4)

        val actionTarget = new Text
        grid.add(actionTarget, 1, 6)

        btn.onMouseClicked = _ => {
          actionTarget.setFill(Color.FireBrick)
          actionTarget.setText("Sign in button pressed")
        }

        grid
      }
    }
  }
}
