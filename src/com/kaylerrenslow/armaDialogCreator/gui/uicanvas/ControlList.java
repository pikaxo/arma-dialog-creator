package com.kaylerrenslow.armaDialogCreator.gui.uicanvas;

import com.kaylerrenslow.armaDialogCreator.util.UpdateListenerGroup;
import org.intellij.lang.annotations.Flow;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 Used for storing controls. This is an alternative to {@link javafx.collections.ObservableList}. Advantages of this over the other is this supports moving. In the other one, in order to "move"
 something, you would have to remove it and then add it back. This would fire 2 events: remove and add. Although this implementation functionally does the same thing, only one event is fired for
 moving and thus makes it easier to detect and manage.

 @author Kayler
 @since 08/12/2016. */
public class ControlList<C extends CanvasControl> implements List<C> {
	private final ArrayList<C> controls = new ArrayList<>();
	private final LinkedList<ControlListChangeListener<C>> listeners = new LinkedList<>();
	private final ControlHolder<C> holder;
	private final UpdateListenerGroup<Object> onClear = new UpdateListenerGroup<>();


	public ControlList(@NotNull ControlHolder<C> holder) {
		this.holder = holder;
	}

	@Override
	public void clear() {
		controls.clear();
		onClear.update(null);
	}

	/** Listeners for when the list is cleared. */
	public UpdateListenerGroup<Object> getOnClear() {
		return onClear;
	}

	@Override
	public C get(int index) {
		return controls.get(index);
	}

	@Override
	public C set(int index, @Flow(targetIsContainer = true) C element) {
		boundTest(index);
		C old = controls.set(index, element);
		ControlListChange<C> change = new ControlListChange<>(this);
		change.setSet(new ControlSet<>(old, element, index));
		notifyListeners(change);
		return old;
	}

	/** Add a control to end of list. Returns true. */
	public boolean add(C control) {
		controls.add(control);
		afterAdd(controls.size() - 1, control);//size -1 because control was already added and size of list has changed
		return true;
	}

	/** Add a control at index */
	public void add(int index, C control) {
		if (index != controls.size()) {
			boundTest(index);
		}
		controls.add(index, control);
		afterAdd(index, control);
	}

	private void afterAdd(int index, C control) {
		ControlListChange<C> change = new ControlListChange<>(this);
		change.setAdded(new ControlAdd<>(control, index));
		notifyListeners(change);
	}

	/** Remove a control. Returns true if control could be located and removed, false otherwise. */
	@Override
	public boolean remove(Object control) {
		int index = controls.indexOf(control);
		if (index < 0) {
			return false;
		}
		remove(index);
		return true;
	}

	/** Removes a control at index */
	public C remove(int index) {
		boundTest(index);
		C removedControl = controls.remove(index);
		ControlListChange<C> change = new ControlListChange<>(this);
		change.setRemoved(new ControlRemove<>(removedControl, index));
		notifyListeners(change);
		return removedControl;
	}

	/**
	 Performs a move operation on this list. This calls {@link #move(int, ControlList, int)} with the index of toMove and this list's control holder as the new parent

	 @param toMove control to move in this list
	 @param newIndex new index to place the control in this list
	 @return true if the operation succeeded, false if it didn't (occurs when control couldn't be found)
	 */
	public boolean move(C toMove, int newIndex) {
		int index = controls.indexOf(toMove);
		if (index < 0) {
			return false;
		}
		move(index, this, newIndex);
		return true;
	}

	/**
	 Performs a move operation on this list. This calls {@link #move(int, ControlList, int)} with the index and this list's control holder as the new parent

	 @param index index of control to move in this list
	 @param newIndex new index to place the control in this list
	 */
	public void move(int index, int newIndex) {
		move(index, this, newIndex);
	}

	/**
	 Moves a control from this list into another ControlList. This method will result in {@link ControlListChange#getMoved()} not being null.

	 @param toMove control to move
	 @param newList list to move control to
	 @param newIndex index of the new holder's control list to move to
	 @return true if the operation was successful, false if it wasn't (happens when the control to move wasn't located).
	 */
	public boolean move(C toMove, ControlList<C> newList, int newIndex) {
		int index = controls.indexOf(toMove);
		if (index < 0) {
			return false;
		}
		move(index, newList, newIndex);
		return true;
	}


	/**
	 Moves a control at index indexOfControlToMove from this list into another ControlList. This method will result in {@link ControlListChange#getMoved()} not being null. When the internal
	 operation is completed, this list's change listeners will be notified and then newList's listeners.

	 @param indexOfControlToMove index of control to move inside this ControlList
	 @param newList list to move the control to
	 @param newParentIndex index of the new holder's control list to move to
	 */
	public void move(int indexOfControlToMove, ControlList<C> newList, int newParentIndex) {
		boundTest(indexOfControlToMove);
		if (indexOfControlToMove == newParentIndex && newList == this) { //not actually moving
			return;
		}
		C toMove = controls.get(indexOfControlToMove);
		controls.remove(indexOfControlToMove);
		if (newParentIndex != newList.size()) {
			newList.boundTest(newParentIndex);
			newList.controls.add(newParentIndex, toMove);
		} else {
			newList.controls.add(toMove);
		}

		ControlListChange<C> change = new ControlListChange<>(this);
		change.setMoved(new ControlMove<>(toMove, this, indexOfControlToMove, newList, newParentIndex));
		change.getMoved().setOriginalUpdate(true);
		notifyListeners(change);
		change.getMoved().setOriginalUpdate(false);
		newList.notifyListeners(change);
	}

	/**
	 Moves a control at index indexOfControlToMove from this list into another ControlList. This method will result in {@link ControlListChange#getMoved()} not being null.

	 @param indexOfControlToMove index of control to move inside this ControlList
	 @param newList list to move the control to (will move control to end of list)
	 */
	public void move(int indexOfControlToMove, ControlList<C> newList) {
		move(indexOfControlToMove, newList, newList.size());
	}

	/**
	 Moves a control from this list into another ControlList. This method will result in {@link ControlListChange#getMoved()} not being null.

	 @param control control to move inside this ControlList
	 @param newList list to move the control to (will move control to end of list)
	 @return true if the operation succeed, or false if it didn't (happens when control couldn't be located)
	 */
	public boolean move(C control, ControlList<C> newList) {
		return move(control, newList, newList.size());
	}

	/** Adds a listener. If the listener already has been added, will not be added again */
	public void addChangeListener(ControlListChangeListener<C> l) {
		if (listeners.contains(l)) {
			return;
		}
		listeners.add(l);
	}

	/** Removes a listener. Returns true if the listener was added and not removed, false otherwise */
	public boolean removeChangeListener(ControlListChangeListener<C> l) {
		return listeners.remove(l);
	}

	private void notifyListeners(ControlListChange<C> change) {
		for (ControlListChangeListener<C> l : listeners) {
			l.onChanged(this, change);
		}
	}

	private void boundTest(int index) {
		if (index < 0 || index >= controls.size()) {
			throw new IndexOutOfBoundsException("index is out of range. index:" + index + " size of list:" + controls.size());
		}
	}

	@NotNull
	public ControlHolder<C> getHolder() {
		return holder;
	}

	@Override
	public int size() {
		return controls.size();
	}

	@Override
	public boolean isEmpty() {
		return controls.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return controls.contains(o);
	}

	@NotNull
	@Override
	public Iterator<C> iterator() {
		return controls.iterator();
	}

	@NotNull
	@Override
	public Object[] toArray() {
		return controls.toArray();
	}

	@NotNull
	@Override
	public <T> T[] toArray(@NotNull T[] a) {
		return controls.toArray(a);
	}

	@Override
	public boolean containsAll(@NotNull Collection<?> c) {
		return controls.containsAll(c);
	}

	@Override
	public boolean addAll(@NotNull @Flow(sourceIsContainer = true, targetIsContainer = true) Collection<? extends C> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, @NotNull @Flow(sourceIsContainer = true, targetIsContainer = true) Collection<? extends C> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(@NotNull Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(@NotNull Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(Object o) {
		return controls.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return controls.lastIndexOf(o);
	}

	@NotNull
	@Override
	public ListIterator<C> listIterator() {
		return controls.listIterator();
	}

	@NotNull
	@Override
	public ListIterator<C> listIterator(int index) {
		return controls.listIterator(index);
	}

	@NotNull
	@Override
	public List<C> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	/** Get an iterator that will traverse all descendants (all controls and all their controls) */
	@NotNull
	public Iterable<C> deepIterator() {
		return new DeepIterator<>(this);
	}

	private static class DeepIterator<C extends CanvasControl> implements Iterable<C>, Iterator<C> {

		private final ControlList<C> list;
		private final LinkedList<C> toVisit = new LinkedList<>();
		private final Iterator<C> primaryListIterator;

		public DeepIterator(@NotNull ControlList<C> list) {
			this.list = list;
			primaryListIterator = list.iterator();
		}

		@Override
		public Iterator<C> iterator() {
			return this;
		}

		@Override
		public boolean hasNext() {
			return primaryListIterator.hasNext() || toVisit.size() > 0;
		}

		@Override
		public C next() {
			if (!hasNext()) {
				throw new IllegalStateException("nothing left to iterate");
			}
			if (primaryListIterator.hasNext()) {
				C next = primaryListIterator.next();
				toVisit(next);
				return next;
			}
			C next = toVisit.pop();
			toVisit(next);
			return next;
		}

		private void toVisit(C next) {
			if (next instanceof CanvasControlGroup) {
				toVisit.addAll(((CanvasControlGroup<C>) next).getControls());
			}
		}
	}
}
