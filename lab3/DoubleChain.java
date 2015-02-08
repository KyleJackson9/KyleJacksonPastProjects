
public class DoubleChain {
	
	private DNode head;

	
	public DoubleChain(double val) {

		head = new DNode(val);

	}

	public DNode getFront() {
		return head;
	}

	/** Returns the last item in the DoubleChain. */		
	public DNode getBack() {
		DNode old = head;
		while (old.next != null){
			old = old.next;
		}
		return old;
	}
	
	/** Adds D to the front of the DoubleChain. */	
	public void insertFront(double d) {
		DNode old = getFront();
		old.prev = new DNode(null, d, old);
		head = old.prev;
	}
	
	/** Adds D to the back of the DoubleChain. */	
	public void insertBack(double d) {
		// DNode old = head;
		// while (old.next != null){
		// 	old = old.next;
		// }
		// old.next = new DNode(head, d, null);

	

		DNode old = getBack();
		old.next = new DNode(old,d,null);
		//this.getBack().next = new DNode(getBack().next, d, null);

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
