package dotenv.util

import logis.LogLevel.*
import logis.LogLevel

/**
 * ProxyMap is designed to allow logging around accessing keys and values.
 */
case class ProxyMap[K, +V](
  map: Map[K, V],
  override val noneLevel: LogLevel = WARN,
  override val someLevel: LogLevel = INFO,
  override val name: String = ""
) extends AbstractProxyMap[K, V](map, noneLevel, someLevel, name)
    with ProxyMapType[K, V] {
  override def removed(key: K): Map[K, V] = map.removed(key)
}
