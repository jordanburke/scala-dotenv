package dotenv.util

import org.log4s.{Info, LogLevel, Logger, Warn}

trait ProxyMapType[K, +V] {

  protected def log: Logger

  protected def map: Map[K, V]

  protected def name: String = ""

  protected def noneLevel: LogLevel = Warn

  protected def someLevel: LogLevel = Info

  private def some(key: K) = s"$name Key found: '$key'"

  private def none(key: K) = s"$name Missing Key: '$key'"

  protected def getImpl(key: K): Option[V] = {
    val v = map.get(key)
    v match {
      case Some(_) =>
        log(someLevel)(some(key))
        v
      case None =>
        log(noneLevel)(none(key))
        v
    }
  }

  protected def getOrElseImpl[V1 >: V](key: K, default: => V1): V1 =
    map.get(key) match {
      case Some(v) =>
        log(someLevel)(some(key))
        v
      case None =>
        val msg = s"$name Optional '$key' not provided. Defaulting to: '$default'"
        log(someLevel)(msg)
        default
    }

}
