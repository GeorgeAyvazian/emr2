package models

import java.util.Date

case class Appointment(date: Date, patient: Patient)

object Appointment {
//  implicit val writer = Json.writes[Appointment]
}