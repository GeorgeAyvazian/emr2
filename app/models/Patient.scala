package models

import play.api.libs.json.Json

case class Patient(pin: Long, firstName: String, lastName: String)

object Patient {

  val patient1 = Patient(143L, "john", "doe")
  val patient2 = Patient(141L, "jane", "doe")

  var patients = collection.mutable.Set[Patient](
    patient1,
    patient2
  )

  def findAll = patients.toList.sortBy(_.pin)

  def findByPin(pin: Long) = patients.find(_.pin == pin)

  def add(patient: Patient) {
    patients += patient
  }

  implicit val writer = Json.writes[Patient]
}