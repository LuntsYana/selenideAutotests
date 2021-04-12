package utilities;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AllureLogger {

    private final Logger logger;

    public AllureLogger(Class clazz) {
        logger = LogManager.getLogger(clazz);
    }

    @Step("{text}")
    public void info(Object text) {
        logger.info(
                ":" + (Thread.currentThread().getStackTrace()[2].getLineNumber() + 1) + " - " + text);
    }

    public void buildLogInfo(Object text) {
        logger.info(
                ":" + (Thread.currentThread().getStackTrace()[2].getLineNumber() + 1) + " - " + text);
    }

    public void debug(Object text) {
        logger.debug(
                ":" + (Thread.currentThread().getStackTrace()[2].getLineNumber() + 1) + " - " + text);
    }

    public void error(Object text) {
        logger.error(
                ":" + (Thread.currentThread().getStackTrace()[2].getLineNumber() + 1) + " - " + text);
    }

    public void warn(Object text) {
        logger.warn(
                ":" + (Thread.currentThread().getStackTrace()[2].getLineNumber() + 1) + " - " + text);
    }

    public void fatal(Object text) {
        logger.fatal(
                ":" + (Thread.currentThread().getStackTrace()[2].getLineNumber() + 1) + " - " + text);
    }
    }