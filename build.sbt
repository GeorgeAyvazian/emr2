

name := "untitled1"

version := "1.0"

libraryDependencies := Seq[ModuleID](
  jdbc,
  "postgresql" % "postgresql" % "9.1-901.jdbc4",
  "org.hibernate" % "hibernate-entitymanager" % "4.1.9.Final", //,
  "org.webjars" %% "webjars-play" % "2.2.1",
  "org.webjars" % "knockout" % "2.3.0"
  //  "org.apache.tomcat.embed" % "tomcat-embed-core" % "7.0.53" % "container",
  //  "org.apache.tomcat.embed" % "tomcat-embed-logging-juli" % "7.0.53" % "container",
  //  "org.apache.tomcat.embed" % "tomcat-embed-jasper" % "7.0.53" % "container"
)

scalacOptions := Seq[String]("-feature", "-language:implicitConversions")

playScalaSettings