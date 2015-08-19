package customs;

/*
import ch.qos.logback.core.joran.spi.NoAutoStart;
import ch.qos.logback.core.rolling.TimeBasedFileNamingAndTriggeringPolicyBase;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;
import ch.qos.logback.core.rolling.TriggeringPolicy;
import ch.qos.logback.core.rolling.TriggeringPolicyBase;
import ch.qos.logback.core.rolling.helper.DateTokenConverter;
import ch.qos.logback.core.rolling.helper.DefaultArchiveRemover;
import ch.qos.logback.core.rolling.helper.FileNamePattern;
*/

import org.apache.log4j.rolling.TriggeringPolicy;

import java.io.File;
import java.util.*;


public class MinutesTriggeringPolicy implements TriggeringPolicy {

    static Calendar dt = Calendar.getInstance();

    @Override
    public boolean isTriggeringEvent(org.apache.log4j.Appender appender, org.apache.log4j.spi.LoggingEvent event, String filename, long fileLength) {
        Calendar rightNow = Calendar.getInstance();
        int minute = rightNow.get(Calendar.MINUTE);
        double a = minute / 15;

        Calendar tmp = Calendar.getInstance();
        tmp.setTime(dt.getTime());
        tmp.add(Calendar.MINUTE, 15);

        if (tmp.getTime().compareTo(rightNow.getTime()) < 0) {
            dt = rightNow;
            return true;
        }

        if (!(rightNow.get(Calendar.DAY_OF_YEAR) == dt.get(Calendar.DAY_OF_YEAR) &&
            rightNow.get(Calendar.HOUR) == dt.get(Calendar.HOUR) &&
            rightNow.get(Calendar.MINUTE) == dt.get(Calendar.MINUTE)) &&
        a % 1 == 0) {
            dt = rightNow;
            return true;
        } else
            return false;
    }

    @Override
    public String toString() {
        return "customs.MinutesTriggeringPolicy";
    }

    public void activateOptions() {
    }
}