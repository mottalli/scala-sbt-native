package test
import com.sun.jna.{Library, Native}

object testlib {
  Native.register("testlib")

  @native def testLibFunction(): Int
}

object testlib_nonative {
  @native def testLibFunction()
}

object Main {
  def main(args: Array[String]): Unit = {
    println("Hola mundo")

    val result = testlib.testLibFunction()
    println(s"Library returned $result")
  }
}
