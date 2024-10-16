public class Main2 {
    public static void main(String[] args) {
        OrderReceiver receiver = new OrderReceiver();

        Command create = new CreateOrder(receiver);
        Command cancel = new CancelOrder(receiver);
        Command change = new ChangeDestination(receiver, "Парк");

        create.execute();
        change.execute();
        cancel.execute();
    }
}

interface Command {
    void execute();
}

class OrderReceiver {
    public void makeOrder() {
        System.out.println("Заказ создан ");
    }

    public void cancelOrder() {
        System.out.println("Заказ отменён ");
    }

    public void changeDestination(String newDest) {
        System.out.println("Пункт назначения изменён на " + newDest);
    }
}

class CreateOrder implements Command {
    private OrderReceiver receiver;

    public CreateOrder(OrderReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.makeOrder();
    }
}

class CancelOrder implements Command {
    private OrderReceiver receiver;

    public CancelOrder(OrderReceiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.cancelOrder();
    }
}

class ChangeDestination implements Command {
    private OrderReceiver receiver;
    private String destination;

    public ChangeDestination(OrderReceiver receiver, String destination) {
        this.receiver = receiver;
        this.destination = destination;
    }

    @Override
    public void execute() {
        receiver.changeDestination(destination);
    }
}
