package controllers

import models.Patient
import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText}
import play.api.i18n.Messages
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller, Flash}

object Patients extends Controller {

  def list = Action { implicit request =>
    val patients = Patient.findAll
    Ok(views.html.list(patients))
    //    throw new Exception("a scandalous error")
    Ok(views.html.index())
  }

  def show(pin: Long) = Action { implicit request =>
    Patient.findByPin(pin).map { patient =>
      Ok(views.html.patients.details(patient))
    }.getOrElse(NotFound)
  }

  private val patientForm: Form[Patient] = Form(
    mapping(
      "firstName" -> nonEmptyText,
      "lastName" -> nonEmptyText
    )
      (Patient.apply)(Patient.unapply)
  )

  def find(searchTerm: String) = Action {
    Ok(Json.toJson(Patient.find(searchTerm)))
  }

  def save = Action { implicit request =>
    val newPatientForm = patientForm.bindFromRequest()

    newPatientForm.fold(
    { form =>
      Redirect(routes.Patients.newPatient()).
        flashing(Flash(form.data) + ("error" -> Messages("validation.errors")))
    }, { newPatient =>
      Patient.add(newPatient)
      Redirect(routes.Patients.show(newPatient.id)).
        flashing("success" -> Messages("patients.new.success", newPatient.id))
    })
  }

  def newPatient = Action { implicit request =>
    val form = if (flash.get("error").isDefined) patientForm.bind(flash.data) else patientForm
    Ok(views.html.patients.editPatient(form))
  }
}