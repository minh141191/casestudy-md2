public interface Manage<E> {
    E create();
    E update();
    E delete();
    E getById();
    void dissplayAll();
}
