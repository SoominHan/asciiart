package org.alcibiade.asciiart.slf4j;

import org.slf4j.Logger;
import org.slf4j.Marker;

public abstract class LoggerDelegator implements Logger {

    private Logger logger;

    public LoggerDelegator(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void warn(Marker marker, String string, Throwable thrwbl) {
        logger.warn(marker, string, thrwbl);
    }

    @Override
    public void warn(Marker marker, String string, Object[] os) {
        logger.warn(marker, string, os);
    }

    @Override
    public void warn(Marker marker, String string, Object o, Object o1) {
        logger.warn(marker, string, o, o1);
    }

    @Override
    public void warn(Marker marker, String string, Object o) {
        logger.warn(marker, string, o);
    }

    @Override
    public void warn(Marker marker, String string) {
        logger.warn(marker, string);
    }

    @Override
    public void warn(String string, Throwable thrwbl) {
        logger.warn(string, thrwbl);
    }

    @Override
    public void warn(String string, Object o, Object o1) {
        logger.warn(string, o, o1);
    }

    @Override
    public void warn(String string, Object[] os) {
        logger.warn(string, os);
    }

    @Override
    public void warn(String string, Object o) {
        logger.warn(string, o);
    }

    @Override
    public void warn(String string) {
        logger.warn(string);
    }

    @Override
    public void trace(Marker marker, String string, Throwable thrwbl) {
        logger.trace(marker, string, thrwbl);
    }

    @Override
    public void trace(Marker marker, String string, Object[] os) {
        logger.trace(marker, string, os);
    }

    @Override
    public void trace(Marker marker, String string, Object o, Object o1) {
        logger.trace(marker, string, o, o1);
    }

    @Override
    public void trace(Marker marker, String string, Object o) {
        logger.trace(marker, string, o);
    }

    @Override
    public void trace(Marker marker, String string) {
        logger.trace(marker, string);
    }

    @Override
    public void trace(String string, Throwable thrwbl) {
        logger.trace(string, thrwbl);
    }

    @Override
    public void trace(String string, Object[] os) {
        logger.trace(string, os);
    }

    @Override
    public void trace(String string, Object o, Object o1) {
        logger.trace(string, o, o1);
    }

    @Override
    public void trace(String string, Object o) {
        logger.trace(string, o);
    }

    @Override
    public void trace(String string) {
        logger.trace(string);
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
        return logger.isWarnEnabled(marker);
    }

    @Override
    public boolean isWarnEnabled() {
        return logger.isWarnEnabled();
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
        return logger.isTraceEnabled(marker);
    }

    @Override
    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
        return logger.isInfoEnabled(marker);
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
        return logger.isErrorEnabled(marker);
    }

    @Override
    public boolean isErrorEnabled() {
        return logger.isErrorEnabled();
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
        return logger.isDebugEnabled(marker);
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public void info(Marker marker, String string, Throwable thrwbl) {
        logger.info(marker, string, thrwbl);
    }

    @Override
    public void info(Marker marker, String string, Object[] os) {
        logger.info(marker, string, os);
    }

    @Override
    public void info(Marker marker, String string, Object o, Object o1) {
        logger.info(marker, string, o, o1);
    }

    @Override
    public void info(Marker marker, String string, Object o) {
        logger.info(marker, string, o);
    }

    @Override
    public void info(Marker marker, String string) {
        logger.info(marker, string);
    }

    @Override
    public void info(String string, Throwable thrwbl) {
        logger.info(string, thrwbl);
    }

    @Override
    public void info(String string, Object[] os) {
        logger.info(string, os);
    }

    @Override
    public void info(String string, Object o, Object o1) {
        logger.info(string, o, o1);
    }

    @Override
    public void info(String string, Object o) {
        logger.info(string, o);
    }

    @Override
    public void info(String string) {
        logger.info(string);
    }

    @Override
    public String getName() {
        return logger.getName();
    }

    @Override
    public void error(Marker marker, String string, Throwable thrwbl) {
        logger.error(marker, string, thrwbl);
    }

    @Override
    public void error(Marker marker, String string, Object[] os) {
        logger.error(marker, string, os);
    }

    @Override
    public void error(Marker marker, String string, Object o, Object o1) {
        logger.error(marker, string, o, o1);
    }

    @Override
    public void error(Marker marker, String string, Object o) {
        logger.error(marker, string, o);
    }

    @Override
    public void error(Marker marker, String string) {
        logger.error(marker, string);
    }

    @Override
    public void error(String string, Throwable thrwbl) {
        logger.error(string, thrwbl);
    }

    @Override
    public void error(String string, Object[] os) {
        logger.error(string, os);
    }

    @Override
    public void error(String string, Object o, Object o1) {
        logger.error(string, o, o1);
    }

    @Override
    public void error(String string, Object o) {
        logger.error(string, o);
    }

    @Override
    public void error(String string) {
        logger.error(string);
    }

    @Override
    public void debug(Marker marker, String string, Throwable thrwbl) {
        logger.debug(marker, string, thrwbl);
    }

    @Override
    public void debug(Marker marker, String string, Object[] os) {
        logger.debug(marker, string, os);
    }

    @Override
    public void debug(Marker marker, String string, Object o, Object o1) {
        logger.debug(marker, string, o, o1);
    }

    @Override
    public void debug(Marker marker, String string, Object o) {
        logger.debug(marker, string, o);
    }

    @Override
    public void debug(Marker marker, String string) {
        logger.debug(marker, string);
    }

    @Override
    public void debug(String string, Throwable thrwbl) {
        logger.debug(string, thrwbl);
    }

    @Override
    public void debug(String string, Object[] os) {
        logger.debug(string, os);
    }

    @Override
    public void debug(String string, Object o, Object o1) {
        logger.debug(string, o, o1);
    }

    @Override
    public void debug(String string, Object o) {
        logger.debug(string, o);
    }

    @Override
    public void debug(String string) {
        logger.debug(string);
    }
}
