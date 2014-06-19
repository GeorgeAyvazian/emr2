package controllers

import java.util.Date
import javax.persistence.Query

import models.{Appointment, Patient, Provider, Schedule}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import play.db.jpa.JPA
import play.libs.F.Callback0

import scala.collection.mutable

object Schedules extends Controller {

  val provider1 = Provider("Betty", "Black")
  val provider2 = Provider("John", "Huckstable")

  implicit def appointmentOrdering: Ordering[Appointment] = Ordering.by[Appointment, Date]((appointment: Appointment) => appointment.date)

  implicit def scheduleOrdering: Ordering[Schedule] = Ordering.by[Schedule, String]((schedule: Schedule) => schedule.provider.firstName)

  import models.Patient._

  val schedules = mutable.SortedSet[Schedule](
    Schedule(provider1, mutable.SortedSet[Appointment](Appointment(new Date, patient1), Appointment(new Date, patient2))),
    Schedule(provider2, mutable.SortedSet[Appointment](Appointment(new Date, patient2)))
  )

  def view = Action {
    def x: Callback0 = new Callback0 {
      override def invoke(): Unit = {
        val createQuery: Query = JPA.em().createQuery("from Patient")
        val patient: Patient = new Patient("a", "b")
        JPA.em().persist(patient)
        import scala.collection.JavaConversions._
        val list: mutable.Buffer[Patient] = asScalaBuffer(createQuery.getResultList).map(p => p.asInstanceOf[Patient])
        println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG")
        list.map(p => p.id) foreach println
        println("GGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG")
      } //JPA.em().createQuery("update Patient set firstName = 'null' where id = 1")
    }

    JPA.withTransaction(x)
    Ok(Json.toJson(schedules))
  }
}