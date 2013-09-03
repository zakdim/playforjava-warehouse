import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "warehouse"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "mysql" % "mysql-connector-java" % "5.1.25"
//    javaJpa,
//    "org.hibernate" % "hibernate-entitymanager" % "3.6.9.Final"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here     

    // Fix for JUnit tests known issue reported for play-2.1.3, see 
    // https://groups.google.com/forum/#!topic/play-framework/Jn9k6osk6V0
    // This might change in the next release.
    testOptions in Test ~= { args =>
      for {
        arg <- args
        val ta: Tests.Argument = arg.asInstanceOf[Tests.Argument]
        val newArg = if(ta.framework == Some(TestFrameworks.JUnit)) ta.copy(args = List.empty[String]) else ta
      } yield newArg
    }    
  )

}
