package kala.alarm.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class Test {
    private static final Logger LOG = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        LOG.debug("erroria pukkaa");
        try {

            throw new AssertionError();
        } catch (AssertionError error) {
            String msg = Arrays.toString(error.getStackTrace());
            LOG.error(msg);
        }
    }
}
