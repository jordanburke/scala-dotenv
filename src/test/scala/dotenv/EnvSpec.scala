package dotenv

import org.specs2.*

class EnvSpec extends mutable.Specification {

  object TestEnv extends Env {
    val FOO: Option[String] = env.get("FOO")
    val BAR: Option[String] = env.get("BAR")
  }

  "When using Env" should {

    "Default USER_HOME is not Null" >> {
      TestEnv.USER_HOME must not(beNull)
    }

    "Default FOO is BAR" >> {
      TestEnv.FOO == Option("BAR")
    }

    "Default BAR is None" >> {
      TestEnv.BAR.isEmpty
    }

  }
}

