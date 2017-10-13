package Lab4_1;

public abstract class Engineer extends Employee implements WorkTime, Project {
    protected int worktime;
    protected int base;
    protected String project;
    protected int budgetOfProj;

    Engineer(int id, String name, int worktime, int base,String project, int budgetOfProj) {
        super(id, name);
        this.worktime = worktime;
        this.base = base;
        this.project = project;
        this.budgetOfProj=budgetOfProj;
    }

    public int WSalary() {
        return worktime*base;
    }

    public double PSalary() {
        return 0.1*budgetOfProj;
    }

    public int getWorktime() {
        return worktime;
    }

    public int getBase(){
        return base;
    }

    public String getProject() {
        return project;
    }

    public int getBudgetOfProj() {
        return budgetOfProj;
    }
}

class Tester extends Engineer{

    Tester(int id, String name, int worktime, int base,String project, int budgetOfProj) {
        super(id, name, worktime, base,project, budgetOfProj);
    }
}

class Programmer extends Engineer{

    Programmer(int id, String name, int worktime, int base,String project, int budgetOfProj) {
        super(id, name, worktime, base,project, budgetOfProj);
    }
}

class TeamLeader extends Engineer implements Heading{

    TeamLeader(int id, String name, int worktime, int base,String project, int budgetOfProj) {
        super(id, name, worktime, base,project, budgetOfProj);
    }

    public int HSalary(int n) {
        return n*2000;
    }

    public double PSalary() {
        return 0.25*budgetOfProj;
    }
}
