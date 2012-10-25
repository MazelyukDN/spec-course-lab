package by.bsu.contacts.dao;

import by.bsu.contacts.domain.Contact;

import java.util.ArrayList;
import java.util.List;

public class UnitOfWork {

    private List newObjects = new ArrayList();
    private List dirtyObjects = new ArrayList();
    private List removedObjects = new ArrayList();

    public void registerNew(Contact obj) throws DataAccessException {
        assertTrue("object not dirty", !dirtyObjects.contains(obj));
        assertTrue("object not removed", !removedObjects.contains(obj));
        assertTrue("object not already registered new", !newObjects.contains(obj));
        newObjects.add(obj);
    }

    public void registerDirty(Contact obj) throws DataAccessException {
        assertTrue("object not removed", !removedObjects.contains(obj));
        if (!dirtyObjects.contains(obj) && !newObjects.contains(obj)) {
            dirtyObjects.add(obj);
        }
    }

    public void registerRemoved(Contact obj) {
        if (newObjects.remove(obj)) return;
        dirtyObjects.remove(obj);
        if (!removedObjects.contains(obj)) {
            removedObjects.add(obj);
        }
    }

    public void commit(){
        for (Object obj : newObjects) {
            ((Contact)obj).save();
        }
        for (Object obj : dirtyObjects) {
            ((Contact)obj).save();
        }
        for (Object obj : removedObjects) {
            ((Contact)obj).delete();
        }
    }

    public void registerClean(Contact obj) {
        //TODO nothing
    }

    private static ThreadLocal current = new ThreadLocal();

    public static void newCurrent() {
        setCurrent(new UnitOfWork());
    }

    public static void setCurrent(UnitOfWork uow) {
        current.set(uow);
    }

    public static UnitOfWork getCurrent() {
        return (UnitOfWork) current.get();
    }

    private void assertTrue(String message, boolean condition) throws DataAccessException {
        if (!condition) {
            throw new DataAccessException(message);
        }
    }
}
