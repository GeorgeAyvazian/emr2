package controllers

import models.Patient
import play.api.Routes
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

object Application extends Controller {
  def index = Action {
    Redirect(routes.Patients.list())
  }

  def findAllPatients(term: String) = Action {
    Ok(Json.toJson(Patient.find(term)))
  }

  object AppPatient extends Controller {

  }

  def javascriptRoutes = Action { implicit request =>
    Ok(Routes.javascriptRouter("appRoutes")(
      controllers.routes.javascript.Application.findAllPatients)).as("text/javascript")
  }

}
