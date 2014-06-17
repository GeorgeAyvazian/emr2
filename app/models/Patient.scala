package models

import javax.persistence._

@Entity
@Table(name = "patients")
class Patient() {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  var pin: Long = _

  var firstName: String = _
  var lastName: String = _
}

object Patient {

  val patient1 = new Patient
  patient1.firstName = "John"
  patient1.lastName = "doe"
  val patient2 = new Patient
  patient2.firstName = "Jane"
  patient2.lastName = "doe"

  var patients = collection.mutable.Set[Patient](
    patient1,
    patient2
  )

  def findAll = patients.toList.sortBy(_.pin)

  def findByPin(pin: Long) = patients.find(_.pin == pin)

  def add(patient: Patient) {
    patients += patient
  }

}