public interface Loadable<T> {
     void loadObject(T object);
     T unloadObject();
}
