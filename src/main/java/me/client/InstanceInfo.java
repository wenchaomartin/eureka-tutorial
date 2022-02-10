package me.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * 应用实例信息
 *
 * @author songwenchao
 */
public class InstanceInfo {
    private static final Logger logger = LoggerFactory.getLogger(InstanceInfo.class);

    private String appName;

    private String instanceId;

    private String ip;

    private int port;

    private String hostName;

    private InstanceStatus instanceStatus;

    public enum InstanceStatus {
        UP,
        DOWN,
        UNKNOWN;

        public InstanceStatus toEnum(String s) {
            if (s != null) {
                try {
                    return InstanceStatus.valueOf(s.toUpperCase());
                } catch (IllegalArgumentException e) {
                    logger.debug("String {} can't convert instanceStatus and default to UNKNOWN", s);
                }
            }
            return UNKNOWN;

        }


    }

    public static Logger getLogger() {
        return logger;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public InstanceStatus getInstanceStatus() {
        return instanceStatus;
    }

    public void setInstanceStatus(InstanceStatus instanceStatus) {
        this.instanceStatus = instanceStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InstanceInfo)) return false;
        InstanceInfo that = (InstanceInfo) o;
        return Objects.equals(getInstanceId(), that.getInstanceId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInstanceId());
    }
}
