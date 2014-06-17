package models

import java.util.Date

import play.api.libs.json.Json

case class Appointment(date: Date, patient: Patient)

object Appointment {
  implicit val writer = Json.writes[Appointment]
}