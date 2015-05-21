name := "scala-sbt-native"

version := "1.0"

scalaVersion := "2.11.6"

sbtVersion := "0.12.4"

compile <<= (compile in Compile, sourceDirectory, target, classDirectory in Compile).map { (compile, srcDir, targetDir, classDir) => {
  val result = sbt.Process("cmake" :: s"${srcDir}/main/cpp" :: s"-DCMAKE_LIBRARY_OUTPUT_DIRECTORY=$classDir/linux-x86-64" :: Nil, targetDir) #&&
    sbt.Process("cmake" :: "--build" :: targetDir.toString :: Nil, targetDir) !

  if (result != 0)
    error("Error compiling wrapper library!")

  compile
}}

fork in run := true
