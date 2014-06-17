package controllers

import java.util
import java.util.Date
import javax.persistence.Query

import models.{Patient, Appointment, Provider, Schedule}
import play.api.mvc.{Action, Controller}
import play.db.jpa.{JPA, Transactional}
import play.libs.F.Callback0

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

  @Transactional("defaultPersistenceUnit")
  def view = Action {
    def x: Callback0 = new Callback0 {
      override def invoke(): Unit = {
        val createQuery: Query = JPA.em().createQuery("from Patient")
        val patient: Patient = new Patient
        patient.firstName = "a"
        patient.lastName = "b"
        JPA.em().persist(patient)
        val list: util.List[_] = createQuery.getResultList
        println(list)
      }//JPA.em().createQuery("update Patient set firstName = 'null' where id = 1")
    }

    JPA.withTransaction(x)
    Ok(views.html.index())
  }
}