package com.r0adkll.swatchbuckler.compose.util

public interface Logger {
  public fun log(
    tag: String?,
    message: String,
  )

  public companion object : Logger {
    // TODO: Setting default logger for development, should be null when shipped
    public var delegate: Logger? = null

    override fun log(
      tag: String?,
      message: String,
    ) {
      delegate?.log(tag, message)
    }
  }
}

public object SystemLogger : Logger {
  override fun log(
    tag: String?,
    message: String,
  ) {
    println(
      tag?.let { "[$it] $message" } ?: message,
    )
  }
}
