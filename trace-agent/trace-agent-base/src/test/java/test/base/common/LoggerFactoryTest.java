package test.base.common;

import org.junit.Test;

import com.wpisen.trace.agent.common.logger.Logger;
import com.wpisen.trace.agent.common.logger.LoggerFactory;

/**
 * Created by wpisen on 16/11/12.
 */
public class LoggerFactoryTest {
    @Test
    public void testGetlogger(){
        Logger logger = LoggerFactory.getLogger(LoggerFactoryTest.class);
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        logger.error(new Exception("exception"));
    }
}
