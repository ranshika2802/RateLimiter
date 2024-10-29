package com.rajat.strategy;

import com.rajat.RateLimiter;
import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowRateLimiter implements RateLimiter {

  private final int maxRequestCount; // max request allowed in the time window
  private final long windowDurationInMillis; // Duration of the time window in milisecond
  private final Deque<Long> requestTimestamps;

  public SlidingWindowRateLimiter(int maxRequestCount, long windowDurationInMillis) {
    this.maxRequestCount = maxRequestCount;
    this.windowDurationInMillis = windowDurationInMillis;
    this.requestTimestamps = new LinkedList<>();
  }

  @Override
  public synchronized boolean allowRequest() {
    // get the current time milis
    long currentTimemillis = System.currentTimeMillis();

    // remove timestamps that are outside the sliding window
    while (!requestTimestamps.isEmpty()
        && currentTimemillis - requestTimestamps.peekFirst() > windowDurationInMillis) {
      requestTimestamps.pollFirst();
    }

    //Allow the request within the limit
    if(requestTimestamps.size()<maxRequestCount) {
      requestTimestamps.addLast(currentTimemillis);
      return true;
    }
//  deny the request if limit is reached
    return false;
  }
}
