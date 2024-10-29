package com.rajat.strategy;

import com.rajat.RateLimiter;
import java.util.concurrent.atomic.AtomicInteger;

public class FixedWindowRateLimiter implements RateLimiter {

  private final int maxRequestCount;
  private final long windowDurationMillis;
  private final AtomicInteger requestCount = new AtomicInteger(0);
  private long windowStartTimeMillis;

  public FixedWindowRateLimiter(int maxRequests, long windowDurationMillis) {
    this.maxRequestCount = maxRequests;
    this.windowDurationMillis = windowDurationMillis;
    this.windowStartTimeMillis = System.currentTimeMillis();
  }

  @Override
  public synchronized boolean allowRequest() {
    long currentTimeMillis = System.currentTimeMillis();
    //      check if the current window has expired
    if (currentTimeMillis - windowStartTimeMillis >= windowDurationMillis) {
      windowStartTimeMillis = currentTimeMillis;
      requestCount.set(0);
    }
    // Allow the request if the count is below the limit
    if (requestCount.get() < maxRequestCount) {
      requestCount.incrementAndGet();
      return true;
    }
    return false;
  }
}
