package me.client;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author songwenchao
 */
public class Registry {
    private ConcurrentHashMap<String, ConcurrentHashMap<String, Lease<InstanceInfo>>> appName2InstanceInfo
            = new ConcurrentHashMap<>();

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
}
