// Make sure to make this class a part of the synthesizer package
//package <package name>;
package synthesizer;

public class ArrayRingBuffer extends AbstractBoundedQueue {
  /* Index for the next dequeue or peek. */
  private int first;           
  /* Index for the next enqueue. */
  private int last;             
  /* Array for storing the buffer data. */
  private double[] rb;
  // private int capacity;
  // private int fillCount;

  /** Create a new ArrayRingBuffer with the given capacity. */
  public ArrayRingBuffer(int capacity) {
    this.capacity = capacity;
    rb = new double[capacity];
    first =0;
    last =0;
    fillCount = 0;
    // TODO: Create new array with capacity elements.
    //       first, last, and fillCount should all be set to 0. 
    //       this.capacity should be set appropriately. Note that the local variable
    //       here shadows the field we inherit from AbstractBoundedQueue.
  }

  /** Adds x to the end of the ring buffer. If there is no room, then
    * throw new RuntimeException("Ring buffer overflow") 
    */
  public void enqueue(double x) {
    if (capacity == fillCount){
      throw new RuntimeException("Ring buffer overflow");
    }
    else if ( last != capacity-1){
      rb[last +1] = x;
      last = last + 1;
      fillCount = fillCount + 1;
    } else if(last == capacity-1) {
      rb[0] = x;
      last = 0;
      fillCount = fillCount +1;
    }
  }


  /** Dequeue oldest item in the ring buffer. If the buffer is empty, then
    * throw new RuntimeException("Ring buffer underflow");
    */
  public double dequeue() {
    
    if (fillCount == 0){
        throw new RuntimeException("Ring buffer underflow");
    } else if ( first != capacity-1){ 
       double x = rb[first];
      first = first +1;
      fillCount = fillCount -1;
      return x;
    } else {
      double x = rb[first];
      first = 0;
      fillCount = fillCount -1;
      return x;
    }
    
      
  }

  /** Return oldest item, but don't remove it. */
  public double peek() {
    if (fillCount == 0){
      throw new RuntimeException("Ring buffer underflow");
    } else{
    return rb[first];
  }
}

}
