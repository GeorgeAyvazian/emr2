package controllers

import models.Patient
import play.api.data.Form
import play.api.data.Forms.{mapping, nonEmptyText}
import play.api.i18n.Messages
import play.api.mvc.{Action, Controller, Flash}

object Patients extends Controller {

  def list = Action { implicit request =>
    val patients = Patient.findAll
    Ok(views.html.list(patients))
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
      ((_1, _2)=>new Patient)((p:Patient) => Option(p.firstName, p.lastName))
  )

  def save = Action { implicit request =>
    val newPatientForm = patientForm.bindFromRequest()

    newPatientForm.fold(
    { form =>
      Redirect(routes.Patients.newPatient()).
        flashing(Flash(form.data) + ("error" -> Messages("validation.errors")))
    }, { newPatient =>
      Patient.add(newPatient)
      Redirect(routes.Patients.show(newPatient.pin)).
        flashing("success" -> Messages("patients.new.success", newPatient.pin))
    })
  }

  def newPatient = Action { implicit request =>
    val form = if (flash.get("error").isDefined) patientForm.bind(flash.data) else patientForm
    Ok(views.html.patients.editPatient(form))
  }
}