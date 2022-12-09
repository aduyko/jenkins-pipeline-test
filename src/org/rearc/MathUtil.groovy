package org.rearc

class MathUtil implements Serializable {
  public static final int DEFAULT_NUM = 0

  public static int addOne(int x) {
    return x+2
  }

  public static int random() {
    return Math.round(Math.random() * 100)
  }
}
