import java.util.Iterator;
import java.util.List;
import java.util.Arrays;

public class Main3 {
    public static void main(String[] args) {
        List<Driver> drivers = Arrays.asList(
            new Driver("Иван", 5),
            new Driver("Петр", 2),
            new Driver("Сергей", 10)
        );

        DriverIterator iterator = new DriverIterator(drivers);

        while (iterator.hasNext()) {
            Driver driver = iterator.next();
            System.out.println("Водитель " + driver.name + ", " + driver.distance + " км до клиента");
        }
    }
}
class Driver {
    String name;
    int distance;

    public Driver(String name, int distance) {
        this.name = name;
        this.distance = distance;
    }
}

class DriverIterator implements Iterator<Driver> {
    private List<Driver> drivers;
    private int index = 0;

    public DriverIterator(List<Driver> drivers) {
        this.drivers = drivers;
        this.drivers.sort((d1, d2) -> Integer.compare(d1.distance, d2.distance));
    }

    @Override
    public boolean hasNext() {
        return index < drivers.size();
    }

    @Override
    public Driver next() {
        return drivers.get(index++);
    }
}
