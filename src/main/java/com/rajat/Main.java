package com.rajat;

import com.rajat.strategy.FixedWindowRateLimiter;
import com.rajat.strategy.SlidingWindowRateLimiter;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    System.out.println("Hello FixedWindowRateLimiter Algo!");
    RateLimiter fixedWindowRateLimiter = new FixedWindowRateLimiter(5, 60000);
    for (int i = 0; i < 10; i++) {
      System.out.println(
          "Request " + i + " : " + (fixedWindowRateLimiter.allowRequest() ? "Allowed" : "Blocked"));
      Thread.sleep(10000);
    }
    System.out.println("Hello FixedWindowRateLimiter Algo!");
    RateLimiter slidingWindowRateLimiter = new SlidingWindowRateLimiter(5, 60000);
    for (int i = 0; i < 10; i++) {
      System.out.println(
          "Request "
              + i
              + " : "
              + (slidingWindowRateLimiter.allowRequest() ? "Allowed" : "Blocked"));
      Thread.sleep(10000);
    }
  }
}
