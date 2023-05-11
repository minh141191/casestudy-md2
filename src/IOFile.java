import java.util.ArrayList;
import java.util.List;

public interface IOFile<E> {
    void writeBinary(List<E> e, String path);
    ArrayList<E> readBinary(String path);
}
