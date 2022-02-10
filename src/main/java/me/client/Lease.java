package me.client;

/**
 * @author songwenchao
 */
public class Lease<T> {

    public static final int DEFAULT_DURATION = 60;

    private T holder;

    private Long registerTimeMills;

    private Long updateTimeMills;

    private Long durationInSecsMills;

    private Long evictTimeMills;

    public Lease(T holder, int durationInSecs) {
        this.holder = holder;
        this.registerTimeMills = System.currentTimeMillis();
        this.updateTimeMills = registerTimeMills;
        this.durationInSecsMills = (long) durationInSecs * 1000;
    }

    public void renew() {
        updateTimeMills = System.currentTimeMillis();
    }

    public void cancel() {
        evictTimeMills = System.currentTimeMillis();
    }

    public boolean isExpired() {
        return evictTimeMills > 0 || System.currentTimeMillis() >= registerTimeMills + durationInSecsMills
    }
}
