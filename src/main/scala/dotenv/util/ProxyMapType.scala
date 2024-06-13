package dotenv.util

import logis.LogLevel.*
import logis.{LogLevel, Logger}

trait ProxyMapType[K, +V] {

  protected def log: Logger

  protected def map: Map[K, V]

  protected def name: String = ""

  protected def noneLevel: LogLevel = WARN

  protected def someLevel: LogLevel = INFO

  private def some(key: K) = s"$name Key found: '$key'"

  private def none(key: K) = s"$name Missing Key: '$key'"

  protected def getImpl(key: K): Option[V] = {
    val v = map.get(key)
    v match {
      case Some(_) =>
        log.log(someLevel)(some(key))
        v
      case None =>
        log.log(noneLevel)(none(key))
        v
    }
  }

  protected def getOrElseImpl[V1 >: V](key: K, default: => V1): V1 =
    map.get(key) match {
      case Some(v) =>
        log.log(someLevel)(some(key))
        v
      case None =>
        val msg = s"$name Optional '$key' not provided. Defaulting to: '$default'"
        log.log(someLevel)(msg)
        default
    }

}
