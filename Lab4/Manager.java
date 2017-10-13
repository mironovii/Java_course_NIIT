package Lab4_1;

public class Manager extends Employee implements Project{
    protected String project;
    protected int budgetOfProj;

    Manager(int id, String name, String project, int budgetOfProj) {
        super(id, name);
        this.project = project;
        this.budgetOfProj=budgetOfProj;
    }
    public String getProject() {
        return project;
    }

    public int getBudgetOfProj() {
        return budgetOfProj;
    }

    public double PSalary() {
        return 0.8*budgetOfProj;
    }

}

class ProjectManager extends Manager implements Heading{

    ProjectManager(int id, String name, String project, int budgetOfProj) {
        super(id, name, project, budgetOfProj);
    }

    public double PSalary() {
        return 0.5*budgetOfProj;
    }

    public int HSalary(int n) {
        return n*2000;
    }
}

class SeniorManager extends ProjectManager implements Project, Heading{

   SeniorManager(int id, String name, String project, int budgetOfProj) {
        super(id, name, project, budgetOfProj);

    }

    public double PSalary() {
        return 0.35*budgetOfProj;
    }

}

