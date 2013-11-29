/*
 * Simple preformance benchmark and algorythms try-out:
 * https://github.com/piwicode/algorithms
 */
package org.piwicode.bench.framework;

import java.lang.management.ManagementFactory;
import javax.management.JMException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

/**
 *
 * @author Pierre
 */
public abstract class GCWatch {
    public static  GCWatch instance(){
        return instance;
    }
    private static GCWatch instance=create();    
    private static GCWatch create() {
        try {
            final MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            final ObjectName scavenge = new ObjectName("java.lang:type=GarbageCollector,name=PS Scavenge");
            return new GCWatch() {
                @Override
                long getGCCount() {
                    try {
                        return (Long) mbs.getAttribute(scavenge, "CollectionCount");
                    } catch (JMException ex) {
                        return -1;
                    }
                }
            };
        } catch (MalformedObjectNameException ex) {
            System.out.println("Garbage collection monitoring disabeled");
            return new GCWatch() {
                @Override
                long getGCCount() {
                    return -1;
                }
            };
        }
    }

    abstract long getGCCount();
}
