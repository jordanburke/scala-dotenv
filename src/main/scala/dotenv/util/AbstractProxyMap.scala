package dotenv.util

import logis.LogLevel.*
import logis.{LogLevel, Logger}

abstract class AbstractProxyMap[K, +V](
  map: Map[K, V],
  override val noneLevel: LogLevel = WARN,
  override val someLevel: LogLevel = INFO,
  override val name: String = ""
) extends Map[K, V]
    with ProxyMapType[K, V] {

  override protected val log: Logger = Logger[AbstractProxyMap[K, V]]
  override def updated[V1 >: V](key: K, value: V1): Map[K, V1] = map.updated(key, value)
  override def get(key: K): Option[V] = getImpl(key)
  override def getOrElse[V1 >: V](key: K, default: => V1): V1 = getOrElseImpl(key, default)
  override def iterator: Iterator[(K, V)] = map.iterator
}
