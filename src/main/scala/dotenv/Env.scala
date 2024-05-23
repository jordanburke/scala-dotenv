package dotenv

import dotenv.util.ProxyMap
import io.github.cdimascio.dotenv.Dotenv
import org.log4s.{getLogger, Logger}

import scala.jdk.CollectionConverters._

/**
 * New Env Composable Approach. Implementing Services/Libs can pick which ENV variables they want to use.
 */
trait Env {

  protected val log: Logger = getLogger

  // Configure dotenv
  private val envFile = Dotenv.configure()

  // Load environment variables
  protected val env: Map[String, String] = {
    val dotenv = try {
      val dot = envFile.load()
      log.warn("Loading .env file, this is NOT valid for Production")
      dot
    } catch {
      case _: Throwable =>
        log.info("No .env file found, this IS valid for Production")
        envFile.ignoreIfMissing().load()
    }

    val entries = dotenv.entries().iterator().asScala
    // Use a Proxy Map to log missing values
    ProxyMap(
      entries.foldLeft(Map[String, String]()) { (acc, entry) =>
        acc + (entry.getKey -> entry.getValue)
      },
      name = "Env"
    )
  }

  // Determine if the OS is Windows
  private val WINDOWS: Boolean = System.getProperty("os.name").toLowerCase.contains("win")

  // Determine the user home directory based on the OS
  private val WINDOWS_HOME: Option[String] = {
    val path = (env.get("HOMEDRIVE") ++ env.get("HOMEPATH")).mkString
    if (path.nonEmpty) Some(path) else None
  }

  val USER_HOME: String = (if (WINDOWS) WINDOWS_HOME else env.get("HOME")).getOrElse {
    throw new RuntimeException("Unknown User Home Variable!")
  }
}
