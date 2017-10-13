package Lab4_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        List<Employee> staff = new ArrayList<Employee>();
        parserStaff(staff);
        fillandPrintStaff(staff);
    }

    static void parserStaff(List<Employee> staff) {
        try (BufferedReader fp = new BufferedReader(new FileReader("Staff.csv"))) {
            String buf[];
            while (fp.ready()) {
                buf = fp.readLine().split(",");
                switch (buf[2]) {
                    case "Cleaner":
                        staff.add(new Cleaner(Integer.parseInt(buf[0]), buf[1], Integer.parseInt(buf[3]), Integer.parseInt(buf[4])));
                        break;
                    case "Driver":
                        staff.add(new Driver(Integer.parseInt(buf[0]), buf[1], Integer.parseInt(buf[3]), Integer.parseInt(buf[4])));
                        break;
                    case "Tester":
                        staff.add(new Tester(Integer.parseInt(buf[0]), buf[1], Integer.parseInt(buf[3]), Integer.parseInt(buf[4]), buf[5], Integer.parseInt(buf[6])));
                        break;
                    case "Programmer":
                        staff.add(new Programmer(Integer.parseInt(buf[0]), buf[1], Integer.parseInt(buf[3]), Integer.parseInt(buf[4]), buf[5], Integer.parseInt(buf[6])));
                        break;
                    case "TeamLeader":
                        staff.add(new TeamLeader(Integer.parseInt(buf[0]), buf[1], Integer.parseInt(buf[3]), Integer.parseInt(buf[4]), buf[5], Integer.parseInt(buf[6])));
                        break;
                    case "Manager":
                        staff.add(new Manager(Integer.parseInt(buf[0]), buf[1], buf[5], Integer.parseInt(buf[6])));
                        break;
                    case "ProjectManager":
                        staff.add(new ProjectManager(Integer.parseInt(buf[0]), buf[1], buf[5], Integer.parseInt(buf[6])));
                        break;
                    case "SeniorManager":
                        staff.add(new SeniorManager(Integer.parseInt(buf[0]), buf[1], buf[5], Integer.parseInt(buf[6])));
                        break;
                    default:
                        System.out.println("ERROR!");
                        break;
                }
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    static void fillandPrintStaff(List<Employee> staff) {
        double p1, h;
        String position;
        System.out.printf("%5s|%8s|%15s|%10s|%6s|%12s|%10s|%10s|%n", "ID", "NAME", "POSITION", "WORKTIME", "BASE",
                "PROJECT", "BUDGET", "PAYMENT");
        System.out.printf("%5s|%8s|%15s|%10s|%6s|%12s|%10s|%10s|%n", "-----", "--------", "---------------", "----------",
                "------", "------------", "----------", "----------");
        for (int i = 0; i < staff.size(); i++) {
            switch (position = staff.get(i).getClass().getSimpleName()) {
                case "Cleaner":
                    Cleaner cleaner = (Cleaner) staff.get(i);
                    cleaner.payment = cleaner.WSalary();
                    System.out.printf("%5s|%8s|%15s|%10s|%6s|%12s|%10s|%10s|%n", cleaner.getId(), cleaner.getName(),
                            position, cleaner.getWorktime(), cleaner.getBase(), "null", "null", cleaner.getPayment());
                    break;
                case "Driver":
                    Driver driver = (Driver) staff.get(i);
                    driver.payment = driver.WSalary();
                    System.out.printf("%5s|%8s|%15s|%10s|%6s|%12s|%10s|%10s|%n", driver.getId(), driver.getName(),
                            position, driver.getWorktime(), driver.getBase(), "null", "null", driver.getPayment());
                    break;
                case "Tester":
                    Tester tester = (Tester) staff.get(i);
                    tester.payment = tester.WSalary() + tester.PSalary();
                    System.out.printf("%5s|%8s|%15s|%10s|%6s|%12s|%10s|%10s|%n", tester.getId(), tester.getName(),
                            position, tester.getWorktime(), tester.getBase(), tester.getProject(), tester.getBudgetOfProj(), tester.getPayment());
                    break;
                case "Programmer":
                    Programmer prog = (Programmer) staff.get(i);
                    prog.payment = prog.WSalary() + prog.PSalary();
                    System.out.printf("%5s|%8s|%15s|%10s|%6s|%12s|%10s|%10s|%n", prog.getId(), prog.getName(),
                            position, prog.getWorktime(), prog.getBase(), prog.getProject(), prog.getBudgetOfProj(), prog.getPayment());
                    break;
                case "TeamLeader":
                    TeamLeader teamL = (TeamLeader) staff.get(i);
                    teamL.payment = teamL.WSalary() + teamL.PSalary() + teamL.HSalary(5);
                    System.out.printf("%5s|%8s|%15s|%10s|%6s|%12s|%10s|%10s|%n", teamL.getId(), teamL.getName(),
                            position, teamL.getWorktime(), teamL.getBase(), teamL.getProject(), teamL.getBudgetOfProj(), teamL.getPayment());
                    break;
                case "Manager":
                    Manager manager = (Manager) staff.get(i);
                    manager.payment = manager.PSalary();
                    System.out.printf("%5s|%8s|%15s|%10s|%6s|%12s|%10s|%10s|%n", manager.getId(), manager.getName(),
                            position, "null", "null", manager.getProject(), manager.getBudgetOfProj(), manager.getPayment());
                    break;
                case "ProjectManager":
                    ProjectManager projMan = (ProjectManager) staff.get(i);
                    projMan.payment = projMan.PSalary() + projMan.HSalary(4);
                    System.out.printf("%5s|%8s|%15s|%10s|%6s|%12s|%10s|%10s|%n", projMan.getId(), projMan.getName(),
                            position, "null", "null", projMan.getProject(), projMan.getBudgetOfProj(), projMan.getPayment());
                    break;
                case "SeniorManager":
                    SeniorManager seniorMan = (SeniorManager) staff.get(i);
                    seniorMan.payment = seniorMan.PSalary() + seniorMan.HSalary(13);
                    System.out.printf("%5s|%8s|%15s|%10s|%6s|%12s|%10s|%10s|%n", seniorMan.getId(), seniorMan.getName(),
                            position, "null", "null", seniorMan.getProject(), seniorMan.getBudgetOfProj(), seniorMan.getPayment());
                    break;
                default:
                    System.out.println("ERROR!");
                    break;
            }
        }
    }
}
