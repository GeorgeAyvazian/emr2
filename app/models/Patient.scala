package models

import java.util
import javax.persistence._

import play.api.libs.json.Json
import play.db.jpa.JPA
import play.libs.F.Callback0

import scala.collection.mutable

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
  def find(searchTerm: Any):mutable.Buffer[Patient] = {
  import scala.collection.JavaConversions._
    val list: java.util.List[Patient] = new util.ArrayList[Patient]()
    def x: Callback0 = new Callback0 {
      override def invoke(): Unit = {
        val createQuery: Query = JPA.em().createQuery("from Patient where firstName like '%"+searchTerm+"%'")
        for(i<- createQuery.getResultList)
          list.add(i.asInstanceOf[Patient])
      } //JPA.em().createQuery("update Patient set firstName = 'null' where id = 1")
    }
    JPA.withTransaction(x)
    asScalaBuffer(list)
  }

}