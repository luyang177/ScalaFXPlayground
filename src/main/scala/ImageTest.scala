import java.io.File
import javax.imageio.ImageIO
import scalafx.application.JFXApp
import scalafx.embed.swing.SwingFXUtils
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.layout.VBox
import scalafx.scene.image.{Image, ImageView}

//build.sbt
//"com.github.jai-imageio" % "jai-imageio-jpeg2000" % "1.3.0"

object ImageTest extends JFXApp {

  stage = new JFXApp.PrimaryStage {
    title = "Image"
    scene = new Scene {
      root = {

        //JPEG 2000
        val input = ImageIO.createImageInputStream(new File("res/1.jp2"))
        val bufferedImage = ImageIO.read(input)
        val img = SwingFXUtils.toFXImage(bufferedImage, null)

        //jpg
        //val image = new Image("file:d:\\1.jp2")

        val image = new Image(img)
        val imageView = new ImageView
        imageView.setImage(image)
        imageView.setFitWidth(600)
        imageView.setFitHeight(600)
        imageView.setPreserveRatio(true)

        new VBox {
          padding = Insets(20)
          children = Seq(imageView)
        }
      }
    }
  }
}
