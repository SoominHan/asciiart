package org.alcibiade.asciiart.jcl;

import org.apache.commons.logging.Log;

public abstract class LogDelegator implements Log {

    private Log log;

    public LogDelegator(Log log) {
        this.log = log;
    }

    @Override
    public void warn(Object o, Throwable thrwbl) {
        log.warn(o, thrwbl);
    }

    @Override
    public void warn(Object o) {
        log.warn(o);
    }

    @Override
    public void trace(Object o, Throwable thrwbl) {
        log.trace(o, thrwbl);
    }

    @Override
    public void trace(Object o) {
        log.trace(o);
    }

    @Override
    public boolean isWarnEnabled() {
        return log.isWarnEnabled();
    }

    @Override
    public boolean isTraceEnabled() {
        return log.isTraceEnabled();
    }

    @Override
    public boolean isInfoEnabled() {
        return log.isInfoEnabled();
    }

    @Override
    public boolean isFatalEnabled() {
        return log.isFatalEnabled();
    }

    @Override
    public boolean isErrorEnabled() {
        return log.isErrorEnabled();
    }

    @Override
    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    @Override
    public void info(Object o, Throwable thrwbl) {
        log.info(o, thrwbl);
    }

    @Override
    public void info(Object o) {
        log.info(o);
    }

    @Override
    public void fatal(Object o, Throwable thrwbl) {
        log.fatal(o, thrwbl);
    }

    @Override
    public void fatal(Object o) {
        log.fatal(o);
    }

    @Override
    public void error(Object o, Throwable thrwbl) {
        log.error(o, thrwbl);
    }

    @Override
    public void error(Object o) {
        log.error(o);
    }

    @Override
    public void debug(Object o, Throwable thrwbl) {
        log.debug(o, thrwbl);
    }

    @Override
    public void debug(Object o) {
        log.debug(o);
    }
}
