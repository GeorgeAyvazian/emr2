package models

import javax.persistence.{GeneratedValue, Id}

import play.api.libs.json.Json

case class Provider(firstName: String, lastName: String) {
  @Id
  @GeneratedValue
  var id: Long = _
}

object Provider {
  implicit val writer  = Json.writes[Provider]
}