package com.lebron.springboot.httpclient;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="httpclient")
@PropertySource("classpath:httpclient.properties")
public class HttpClientProperties {

    private Integer maxTotal;
    private Integer defaultMaxPerRoute;
    private Integer connectTimeout;
    private Integer connectionRequestTimeout;
    private Integer socketTimeout;
    private boolean staleConnectionCheckEnabled;

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getDefaultMaxPerRoute() {
        return defaultMaxPerRoute;
    }

    public void setDefaultMaxPerRoute(Integer defaultMaxPerRoute) {
        this.defaultMaxPerRoute = defaultMaxPerRoute;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public Integer getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(Integer connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public Integer getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public boolean isStaleConnectionCheckEnabled() {
        return staleConnectionCheckEnabled;
    }

    public void setStaleConnectionCheckEnabled(boolean staleConnectionCheckEnabled) {
        this.staleConnectionCheckEnabled = staleConnectionCheckEnabled;
    }

}
