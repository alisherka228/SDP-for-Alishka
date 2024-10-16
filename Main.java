public class Main {
    public static void main(String[] args) {
        Request request = new Request(200, "Центр", true);

        Handler carHandler = new CarHandler();
        Handler moneyHandler = new MoneyHandler();
        Handler locationHandler = new LocationHandler();

        carHandler.setNext(moneyHandler);
        moneyHandler.setNext(locationHandler);

        System.out.println("Результат: " + carHandler.handle(request));
    }
}
class Request {
    int money;
    String location;
    boolean carHere;

    public Request(int money, String location, boolean carHere) {
        this.money = money;
        this.location = location;
        this.carHere = carHere;
    }
}

abstract class Handler {
    protected Handler nextHandler;

    public void setNext(Handler handler) {
        this.nextHandler = handler;
    }

    public abstract boolean handle(Request request);
}

class CarHandler extends Handler {
    @Override
    public boolean handle(Request request) {
        if (!request.carHere) {
            System.out.println("Машин нет ");
            return false;
        }
        System.out.println("Машина найдена");
        return nextHandler == null || nextHandler.handle(request);
    }
}

class MoneyHandler extends Handler {
    @Override
    public boolean handle(Request request) {
        if (request.money < 100) {
            System.out.println("Нет денег");
            return false;
        }
        System.out.println("Деньги есть");
        return nextHandler == null || nextHandler.handle(request);
    }
}

class LocationHandler extends Handler {
    @Override
    public boolean handle(Request request) {
        if (request.location == null || request.location.isEmpty()) {
            System.out.println("Где это вообще?");
            return false;
        }
        System.out.println("Местоположение ок");
        return true;
    }
}
