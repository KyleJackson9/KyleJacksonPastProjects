
public class DoubleChain {
	
	private DNode head;
	// public DNode next;
	// public double val;
	
	public DoubleChain(double val) {
		/* your code here. */
		DNode x = new DNode(val);
		head = x;
		// this.val = val;
		// next = null;

	}

	public DNode getFront() {
		return head;
	}

	/** Returns the last item in the DoubleChain. */		
	public DNode getBack() {

		while (head.next != null){
			head = head.next;
		}
		return head;
	}
	
	/** Adds D to the front of the DoubleChain. */	
	public void insertFront(double d) {
		DNode oldItem = head.next;
		DNode newItem = new DNode(null,d, oldItem);
		head= newItem;
	}
	
	/** Adds D to the back of the DoubleChain. */	
	public void insertBack(double d) {
		DNode old = head;
		while (old.next != null){
			old = old.next;
		}
		old.next = new DNode(head, d, null);
	}
	
	/** Removes the last item in the DoubleChain and returns it. 
	  * This is an extra challenge problem. */
	public DNode deleteBack() {
		/* your code here */
		return null;
	}
	
	/** Returns a string representation of the DoubleChain. 
	  * This is an extra challenge problem. */
	public String toString() {
		/* your code here */		
		return null;
	}

	public static class DNode {
		public DNode prev;
		public DNode next;
		public double val;
		
		private DNode(double val) {
			this(null, val, null);
		}
		
		private DNode(DNode prev, double val, DNode next) {
			this.prev = prev;
			this.val = val;
			this.next =next;
		}
	}
	
}
