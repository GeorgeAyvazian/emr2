package controllers

import java.util.Date

import models.{Appointment, Provider, Schedule}
import play.api.libs.json._
import play.api.mvc.{Action, Controller}

import scala.collection.mutable

object Schedules extends Controller {

  val provider1 = Provider("Betty", "Black")
  val provider2 = Provider("John", "Huckstable")

  import models.Patient._

  implicit def ordering: Ordering[Appointment] = Ordering.by[Appointment, Date]((appointment: Appointment) => appointment.date)

  val schedules = mutable.Set[Schedule](
    Schedule(provider1, mutable.SortedSet[Appointment](Appointment(new Date, patient1), Appointment(new Date, patient2))),
    Schedule(provider2, mutable.SortedSet[Appointment](Appointment(new Date, patient2)))
  )

  def view = Action {
    Ok(Json.toJson(schedules))
  }
}