package dotenv.util

import org.log4s.{getLogger, Info, LogLevel, Logger, Warn}

/**
 * ProxyMap is designed to allow logging around accessing keys and values.
 */
case class ProxyMap[K, +V](
  map: Map[K, V],
  override val noneLevel: LogLevel = Warn,
  override val someLevel: LogLevel = Info,
  override val name: String = ""
) extends AbstractProxyMap[K, V](map, noneLevel, someLevel, name)
    with ProxyMapType[K, V] {
  override def removed(key: K): Map[K, V] = map.removed(key)
}
