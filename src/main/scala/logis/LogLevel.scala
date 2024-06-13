package logis

sealed trait LogLevel

object LogLevel {
  case object ERROR extends LogLevel
  case object WARN extends LogLevel
  case object INFO extends LogLevel
  case object DEBUG extends LogLevel
  case object TRACE extends LogLevel
}
