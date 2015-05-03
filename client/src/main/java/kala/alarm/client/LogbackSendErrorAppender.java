package kala.alarm.client;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

/**
 * <a href="http://logback.qos.ch/">Logback<a/> log appender to be used in addition to normal logging
 * to send errors to kala alarm server. By adding this appender to logback config, you can send errors
 * to the server without modifying your existing logging code.
 *
 * Recommended to only be used for ERROR level logs to avoid unnecessary spam and network traffic.
 */
public class LogbackSendErrorAppender extends AppenderBase<ILoggingEvent> {

    @Override
    protected void append(ILoggingEvent eventObject) {
        //
    }
}
