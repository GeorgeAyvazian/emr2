package models

import play.api.libs.json.Json

import scala.collection.mutable

case class Schedule(provider: Provider, appointments: mutable.SortedSet[Appointment] )

object Schedule {
  implicit val writes = Json.writes[Schedule]
}
