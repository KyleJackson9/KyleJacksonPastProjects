
import java.util.AbstractList;

	public class ArrayList61B<E> extends AbstractList<E> {
	public E[] items;
	public int size;

	private void resize(int c) {
		E[] newItems = (E[]) new Object[c];
		for (int i = 0; i < items.length; i += 1) {
		newItems[i] = items[i];
		}
		items = newItems;
	}

	public ArrayList61B(int initialCapacity) {
		items = (E[]) new Object[initialCapacity];
		size = 0;
	}

	public ArrayList61B() {
		items =(E[]) new Object[1];
		size = 0;
	}
	
	public boolean add(E item) {

		if (size == items.length) {
		resize((int) (size * 2));
		}
		items[size] = item;
		size = size + 1;
		return true;
	}
	
	// public E getBack() {
	// 	return items[size - 1];
	// }

	public E get(int i) {
		return items[i];
	}

	// public E deleteBack() {
	// E oldBack = getBack(); 
	// size = size - 1; 
	// return oldBack;
	// }

	public int size() {
	return size;
}
}
