resolvers ++= Seq("Sonaype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "Rhinofly Internal Release Repository" at "http://maven-repository.rhinofly.net:8081/artifactory/libs-release-local",
  "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/",
  "Sonaype Release" at "https://oss.sonatype.org/content/repositories/release/")

// The Play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.3.7")

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.6.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.3.0")