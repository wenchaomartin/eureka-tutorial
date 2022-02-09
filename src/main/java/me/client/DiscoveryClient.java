package me.client;

import java.util.List;

/**
 * @author songwenchao
 */
public interface DiscoveryClient {
    /**
     * 根据 应用名字获取 多个实例
     *
     * @param appName
     * @return
     */
    List<InstanceInfo> getByAppName(String appName);
}
