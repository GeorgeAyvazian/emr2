package controllers

import play.api.mvc.{Action, Controller}

object Barcodes extends Controller {
  val ImageResolution = 144

  def barcode(pin: Long) = Action {
    val MimeType = "image/png"
    try {
      val imageData = pin13BarCode(pin, MimeType)
      Ok(imageData).as(MimeType)
    } catch {
      case e: IllegalArgumentException =>
        BadRequest("Couldn't generate bar code. Error: " + e.getMessage)
    }
  }

    def pin13BarCode(pin: Long, mimeType: String): Array[Byte] = {

      //      import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider
      //
      //      val output: ByteArrayOutputStream = new ByteArrayOutputStream
      //
      //      val canvas: BitmapCanvasProvider = new BitmapCanvasProvider(output, mimeType, ImageResolution, BufferedImage.TYPE_BYTE_BINARY, false, 0)
      //      val barcode = new EAN13BEan()
      //      barcode.generateBarcode(canvas, String valueOf pin)
      //      canvas.finish()
      //
      //      output.toByteArray
      Array(0, 3, 2, 22222, 1, 34, 234234234).map(_.toByte)
    }
}