package models

import java.util.Date
import javax.persistence._

import play.api.libs.json.Json

import scala.annotation.meta.field

@Entity
@Table(name = "appointments", indexes = Array(new Index (name = "idx_patient_id", columnList = "patient_id")))
case class Appointment(date: Date, @(ManyToOne@field) patient: Patient) {
  @Id
  @GeneratedValue
  var id: Long = _
}

object Appointment {
  implicit val writer = Json.writes[Appointment]
}