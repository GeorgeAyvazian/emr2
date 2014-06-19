package models

import javax.persistence._

import play.api.libs.json.Json

@Entity
@Table(name = "patients")
case class Patient(firstName: String, lastName: String) {
  private def this() = {
    this("", "")
  }

  @Id
  @GeneratedValue
  var id: Long = _
}

object Patient {
  val patient1 = Patient("John", "Doe")
  val patient2 = Patient("Jane", "Fonda")

  implicit def patientOrdering: Ordering[Patient] = Ordering.by[Patient, String]((patient: Patient) => patient.firstName + patient.lastName)

  var patients = collection.mutable.SortedSet[Patient](patient1, patient2)

  def findAll = patients.toList.sortBy(_.id)

  def findByPin(pin: Long) = patients.find(_.id == pin)

  def add(patient: Patient) {
    patients += patient
  }

  implicit val writer = Json.writes[Patient]
}