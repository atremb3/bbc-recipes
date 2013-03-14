import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "recipe-finder"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "org.springframework.batch" % "spring-batch-core" % "2.1.9.RELEASE",
    "org.springframework.batch" % "spring-batch-infrastructure" % "2.1.9.RELEASE"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
