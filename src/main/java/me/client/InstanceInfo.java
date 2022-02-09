package me.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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


}
