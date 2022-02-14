package me.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author songwenchao
 */
public class Registry {

    private static final Logger logger = LoggerFactory.getLogger(Registry.class);

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

    private ConcurrentHashMap<String, ConcurrentHashMap<String, Lease<InstanceInfo>>> appName2InstanceInfo
            = new ConcurrentHashMap<>();

    private Registry() {
        initScheduleTask();
    }

    private void initScheduleTask() {
        scheduledExecutorService.schedule();
    }

    public void registry(String appName, InstanceInfo instanceInfo) {
        Map<String, Lease<InstanceInfo>> id2InstanceInfo = appName2InstanceInfo.get(appName);
        if (id2InstanceInfo == null) {
            ConcurrentHashMap<String, Lease<InstanceInfo>> newId2InstanceInfo = new ConcurrentHashMap<>();
            Lease<InstanceInfo> lease = new Lease(instanceInfo, Lease.DEFAULT_DURATION);
            newId2InstanceInfo.put(instanceInfo.getInstanceId(), lease);

            appName2InstanceInfo.put(appName, newId2InstanceInfo);
        } else {
            Lease<InstanceInfo> lease = new Lease(instanceInfo, Lease.DEFAULT_DURATION);
            id2InstanceInfo.put(instanceInfo.getInstanceId(), lease);
        }
    }

    public void cancel(String appName, String instanceId) {
        Map<String, Lease<InstanceInfo>> existId2InstanceInfoLease = appName2InstanceInfo.get(appName);
        if (existId2InstanceInfoLease != null) {
            logger.error("appName's instance not exists {}", appName);
        }

        existId2InstanceInfoLease.remove(instanceId);

    }

    private void evictExpiredLease() {
        Set<Map.Entry<String, ConcurrentHashMap<String, Lease<InstanceInfo>>>> entrySet
                = appName2InstanceInfo.entrySet();
     
        Iterator<Map.Entry<String, ConcurrentHashMap<String, Lease<InstanceInfo>>>> iterator = entrySet.iterator();
    }


}
