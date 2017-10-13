package Lab4_1;

public class Employee {

    protected int id;
    protected String name;
    protected double payment;

    Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getPayment() {
        return payment;
    }
}

interface WorkTime {
    int WSalary();
}

interface Project {
    double PSalary();
}

interface Heading {
    int HSalary(int n);
}
