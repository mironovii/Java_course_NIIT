package Lab4_1;

public abstract class Personal extends Employee implements WorkTime {

    protected int worktime;
    protected int base;

    Personal(int id, String name, int worktime, int base) {
        super(id, name);
        this.worktime = worktime;
        this.base = base;
    }

    public int getWorktime() {
        return worktime;
    }

    public int getBase(){
        return base;
    }

    public int WSalary() {
        return worktime*base;
    }
}

class Cleaner extends Personal{

    Cleaner(int id, String name, int worktime, int base) {
        super(id, name, worktime, base);
    }
}

class Driver extends Personal{

    Driver(int id, String name, int worktime, int base) {
        super(id, name, worktime, base);
    }
}
