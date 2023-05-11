import java.util.Comparator;

public class PointComparator implements Comparator<Rankings> {
    @Override
    public int compare(Rankings o1, Rankings o2) {
        if (o1.getPoint() > o2.getPoint()) {
            return -1;
        } else if (o1.getPoint() == o2.getPoint()) {
            if (o1.getDifferenceGoals() > o2.getDifferenceGoals()) {
                return -1;
            } else if (o1.getDifferenceGoals() == o2.getDifferenceGoals()) {
                return 0;
            }
        }
        return 1;
    }
}
