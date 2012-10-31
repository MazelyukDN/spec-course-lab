package by.bsu.contacts.valuelisthandler;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public abstract class ValueListHandler implements ValueListIterator {

    protected List list;
    protected ListIterator listIterator;

    public ValueListHandler() {
    }

    protected void setList(List list) throws IteratorException {
        this.list = list;
        if (list != null)
            listIterator = list.listIterator();
        else
            throw new IteratorException("List empty");
    }

    public Collection getList() {
        return list;
    }

    @Override
    public int getSize() throws IteratorException {
        int size = 0;

        if (list != null) {
            size = list.size();
        } else {
            throw new IteratorException("");
        }
        return size;
    }

    @Override
    public Object getCurrentElement() throws IteratorException {

        Object obj = null;
        if (list != null) {
            int currIndex = listIterator.nextIndex();
            obj = list.get(currIndex);
        } else {
            throw new IteratorException();
        }
        return obj;

    }

    @Override
    public List getPreviousElements(int count) throws IteratorException {
        int i = 0;
        Object object = null;
        LinkedList list = new LinkedList();

        if (listIterator != null) {
            while (listIterator.hasPrevious() && (i < count)) {
                object = listIterator.previous();
                list.add(object);
                i++;
            }
        } else
            throw new IteratorException();
        return list;
    }

    @Override
    public List getNextElements(int count) throws IteratorException {
        int i = 0;
        Object object = null;
        LinkedList list = new LinkedList();
        if (listIterator != null) {
            while (listIterator.hasNext() && (i < count)) {
                object = listIterator.next();
                list.add(object);
                i++;
            }
        } else {
            throw new IteratorException();
        }
        return list;
    }

    @Override
    public void resetIndex() throws IteratorException {
        if (listIterator != null) {
            listIterator = list.listIterator();
        } else {
            throw new IteratorException();
        }
    }
}
