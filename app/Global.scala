import play.api.GlobalSettings
import play.api.mvc.Results.{NotFound, InternalServerError}
import play.api.mvc.{RequestHeader, SimpleResult}

import scala.concurrent.Future

object Global extends GlobalSettings {
  override def onHandlerNotFound(request: RequestHeader): Future[SimpleResult] = {
    Future.successful(NotFound(views.html.notFound(request.path)))
  }

  override def onError(request: RequestHeader, ex: Throwable): Future[SimpleResult] = {
    Future.successful(InternalServerError(views.html.error(ex)))
  }
}
