package models

import play.api.libs.json.Json

case class Provider(firstName: String, lastName: String)

object Provider {
  implicit val writer  = Json.writes[Provider]
}