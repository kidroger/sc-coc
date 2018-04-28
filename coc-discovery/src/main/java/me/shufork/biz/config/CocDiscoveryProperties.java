package me.shufork.biz.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@ConfigurationProperties(prefix = "shufork.sc.coc.discovery")
public class CocDiscoveryProperties {


    private boolean enabled = false;

    private boolean enableMetrics = true;

    @NestedConfigurationProperty
    private Fetching clanFetch = new Fetching();

    @NestedConfigurationProperty
    private Fetching playerFetch = new Fetching();

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Fetching getClanFetch() {
        return clanFetch;
    }

    public void setClanFetch(Fetching clanFetch) {
        this.clanFetch = clanFetch;
    }

    public Fetching getPlayerFetch() {
        return playerFetch;
    }

    public void setPlayerFetch(Fetching playerFetch) {
        this.playerFetch = playerFetch;
    }

    public boolean isEnableMetrics() {
        return enableMetrics;
    }

    public void setEnableMetrics(boolean enableMetrics) {
        this.enableMetrics = enableMetrics;
    }

    public static class Fetching{
        private int size = 1;

        private int errorDetect = 10;
        /**
         * first fetch after application startup,in milliseconds
         */
        private long initDelay = 180000L;
        /**
         * fetch rate after first fetch,in milliseconds
         */
        private long rate = 30000L;

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public long getInitDelay() {
            return initDelay;
        }

        public void setInitDelay(long initDelay) {
            this.initDelay = initDelay;
        }

        public long getRate() {
            return rate;
        }

        public void setRate(long rate) {
            this.rate = rate;
        }

        public int getErrorDetect() {
            return errorDetect;
        }

        public void setErrorDetect(int errorDetect) {
            this.errorDetect = errorDetect;
        }
    }
}
