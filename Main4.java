public class Main4 {
    public static void main(String[] args) {
        DispatcherMediator mediator = new DispatcherMediator();
        Client client = new Client(mediator);
        Driver driver = new Driver(mediator);

        mediator.setClient(client);
        mediator.setDriver(driver);

        client.requestCar();
    }
}


interface Mediator {
    void notify(Object sender, String event);
}

class DispatcherMediator implements Mediator {
    private Client client;
    private Driver driver;

    public void setClient(Client client) {
        this.client = client;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public void notify(Object sender, String event) {
        if (event.equals("request_car")) {
            System.out.println("Диспетчер: Клиент хочет машину ");
            driver.arrive();
        } else if (event.equals("car_arrived")) {
            System.out.println("Диспетчер: Водитель приехал ");
            client.go();
        }
    }
}

class Client {
    private Mediator mediator;

    public Client(Mediator mediator) {
        this.mediator = mediator;
    }

    public void requestCar() {
        System.out.println("Клиент: Дайте машину!");
        mediator.notify(this, "request_car");
    }

    public void go() {
        System.out.println("Клиент: Еду в точку назначения ");
    }
}

class Driver {
    private Mediator mediator;

    public Driver(Mediator mediator) {
        this.mediator = mediator;
    }

    public void arrive() {
        System.out.println("Водитель: Я приехал!");
        mediator.notify(this, "car_arrived");
    }
}

