// put this at the top of the file
import scalariform.formatter.preferences._

// Resolvers
resolvers ++= Seq(
  //"scalaz bintray" at "https://dl.bintray.com/scalaz/releases",
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots")
)

// Dependencies
val rootDependencies = Seq(
  "org.scala-lang.modules" %% "scala-java8-compat" % "0.7.0",
  "org.scala-lang"         %  "scala-reflect"      % "2.11.7"
)

val testDependencies = Seq(
  "org.specs2"             %% "specs2-core"        % "3.6.4" % "test"
)

val dependencies =
  rootDependencies ++
  testDependencies

// Settings
//
val compileSettings = Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-language:_",
  "-unchecked",
  "-Xlint",
  "-target:jvm-1.8",
  "-Ybackend:GenBCode",
  "-Ydelambdafy:method",
  "-Xfuture"
)

val forkedJvmOption = Seq(
  "-server",
  "-Dfile.encoding=UTF8",
  "-Duser.timezone=GMT",
  "-Xss1m",
  "-Xms2048m",
  "-Xmx2048m",
  "-XX:+CMSClassUnloadingEnabled",
  "-XX:ReservedCodeCacheSize=256m",
  "-XX:+DoEscapeAnalysis",
  "-XX:+UseConcMarkSweepGC",
  "-XX:+UseParNewGC",
  "-XX:+UseCodeCacheFlushing",
  "-XX:+UseCompressedOops"
)

val formatting =
  FormattingPreferences()
    .setPreference(AlignParameters, true)
    .setPreference(AlignSingleLineCaseStatements, false)
    .setPreference(AlignSingleLineCaseStatements.MaxArrowIndent, 40)
    .setPreference(CompactControlReadability, false)
    .setPreference(CompactStringConcatenation, false)
    .setPreference(DoubleIndentClassDeclaration, true)
    .setPreference(FormatXml, true)
    .setPreference(IndentLocalDefs, false)
    .setPreference(IndentPackageBlocks, true)
    .setPreference(IndentSpaces, 2)
    .setPreference(IndentWithTabs, false)
    .setPreference(MultilineScaladocCommentsStartOnFirstLine, false)
    .setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk, false)
    .setPreference(PreserveSpaceBeforeArguments, false)
    .setPreference(RewriteArrowSymbols, false)
    .setPreference(SpaceBeforeColon, false)
    .setPreference(SpaceInsideBrackets, false)
    .setPreference(SpaceInsideParentheses, false)
    .setPreference(SpacesWithinPatternBinders, true)

val pluginsSettings =
  scalariformSettings

val settings = Seq(
  name := "address-book",
  version := "1.0.0",
  scalaVersion := "2.11.7",
  libraryDependencies ++= dependencies,
  fork in run := true,
  fork in Test := true,
  fork in testOnly := true,
  connectInput in run := true,
  javaOptions in run ++= forkedJvmOption,
  javaOptions in Test ++= forkedJvmOption,
  scalacOptions := compileSettings,
  scalacOptions in Test ++= Seq("-Yrangepos"),
  parallelExecution in Test := false,
  // formatting
  //
  ScalariformKeys.preferences := formatting
)

lazy val main =
  project
    .in(file("."))
    .settings(
      pluginsSettings ++ settings:_*
    )
