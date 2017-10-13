/*
Lab3. Разработать проект DekanatDemo. Состоящий из трех классов: Student, Group и Dekanat.
*/
package Lab_java;

import java.io.*;
import java.util.Random;
import java.util.Date;
import java.text.DateFormat;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


class Student {
    final int marksLen = 50;
    private int ID;
    private String ID_group;
    private String FIO;
    Group group = null;
    private int[] marks = new int[marksLen];
    private int numMarks = -1;

    Student(int ID, String FIO) {
        this.ID = ID;
        this.FIO = FIO;
    }

    public String getFIO() {
        return FIO;
    }

    public int getID() {
        return ID;
    }

    public String getID_group() {
        return ID_group;
    }

    public void enrollInGroup(Group group) {
        this.group = group;
        ID_group = group.getTitle();
    }

    public int setMark(int mark) {
        if (mark > 0 && mark < 6) {
            int i = 0;
            while (marks[i] != 0)
                i++;
            marks[i] = mark;
            numMarks = i + 1;
            return 0;
        } else
            return 1;
    }

    public int getMark(int index) {
        return marks[index];
    }

    public double getAverageMark() {
        if (marks[0] != 0) {
            int i = 0, sum = 0;
            while (marks[i] != 0 && i < marks.length) {
                sum += marks[i];
                i++;
            }
            numMarks = i + 1;
            return sum / (i + 1);
        } else
            return -1;
    }

    public int getNumMarks() {
            return numMarks;
    }

    public int discardGroup() {
        if (group.findStudentByFIO(getFIO()) != -1) {
            group = null;
            ID_group = "null";
            return 0;
        } else
            return 1;
    }
}

class Group {

    private String title;
    private Student[] studentsInGroup = new Student[11];
    private int numStudents = -1;
    private Student head = null;

    Group(String title) {
        this.title = title;
    }

    public Student getHead (){
        return head;
    }

    public String getTitle() {
        return title;
    }

    public int setStudent(Student student) {
        if (student != null) {
            int i = 0;
            while (studentsInGroup[i] != null && i < studentsInGroup.length)
                i++;
            studentsInGroup[i] = student;
            numStudents = i + 1;
            return 0;
        } else
            return 1;
    }

    public Student getStudent(int n) {
        if (n >= 0 && n < studentsInGroup.length)
            return studentsInGroup[n];
        return null;
    }

    public int electionHead() {
        if (numStudents > 0) {
            Random rnd = new Random();
            head = studentsInGroup[rnd.nextInt(numStudents) + 1];
            return 0;
        } else
            return 1;
    }

    public String findStudentByID(int id) {
        if (numStudents > 0) {
            int i = 0;
            while (studentsInGroup[i] != null && i < studentsInGroup.length) {
                if (studentsInGroup[i].getID() == id)
                    break;
                i++;
            }
            return studentsInGroup[i].getFIO();
        } else
            return "null";
    }

    public int findStudentByFIO(String fio) {
        if (numStudents > 0) {
            int i = 0;
            while (studentsInGroup[i] != null && i < studentsInGroup.length) {
                if (studentsInGroup[i].getFIO().equals(fio))
                    break;
                i++;
            }
            return i + 1;
        } else
            return -1;
    }

    public double averageScore() {
        if (numStudents > 0) {
            double sum = 0;
            int i = 0, k = 0;
            while (studentsInGroup[i] != null && i < studentsInGroup.length) {
                if (studentsInGroup[i].getAverageMark() != -1) {
                    sum += studentsInGroup[i].getAverageMark();
                } else
                    k++;
                i++;
            }
            return sum / (i - k);
        } else
            return -1;
    }

    public int exclusionOfStudent(String FIO) {
        int index = findStudentByFIO(FIO) - 1;
        if (index != -1) {
            while (studentsInGroup[index] != null && index < studentsInGroup.length) {
                if (index == studentsInGroup.length - 1)
                    studentsInGroup[index] = null;
                else
                    studentsInGroup[index] = studentsInGroup[index + 1];
                index++;
            }
            numStudents -= 1;
            return 0;
        } else
            return 1;
    }

    public int getNumStudents() {
        return numStudents;
    }

}

class Dekanat {

    private Group[] groups = new Group[3];
    private Student[] allStudents = new Student[30];
    private int dNumGroups;
    private int dNumStudents;

    public void fillStudents(File f) {
        try {
            JSONParser parser = new JSONParser();
            FileReader fr = new FileReader(f);
            Object obj = parser.parse(fr);
            JSONObject js = (JSONObject) obj;
            JSONArray items = (JSONArray) js.get("Students");
            int count = 0;
            for (Object i : items) {
                Object str = ((JSONObject) i).get("fio");
                Object id = ((JSONObject) i).get("id");
                allStudents[count] = new Student(Integer.parseInt(id.toString()), str.toString());
                count++;
            }
            dNumStudents = count;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void fillGroups(File f) {
         try {
            JSONParser parser = new JSONParser();
            FileReader fr = new FileReader(f);
            Object obj = parser.parse(fr);
            JSONObject js = (JSONObject) obj;
            JSONArray items = (JSONArray) js.get("Groups");
            int count = 0;
            for (Object i : items) {
                Object group = ((JSONObject) i).get("GroupName");
                JSONArray list = (JSONArray) js.get(group.toString());
                groups[count] = new Group(group.toString());
                for (Object j : list) {
                    Object str = ((JSONObject) j).get("id");
                    groups[count].setStudent(allStudents[Integer.parseInt(str.toString()) - 1]);
                    allStudents[Integer.parseInt(str.toString()) - 1].enrollInGroup(groups[count]);
                    groups[count].findStudentByID(Integer.parseInt(str.toString()));
                }
                count++;
            }
            dNumGroups = count;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void genRndMarks() {
        Random rnd = new Random();
        for (int i = 0; i < allStudents.length; i++)
            for (int j = 0; j < allStudents[i].marksLen; j++)
                allStudents[i].setMark(rnd.nextInt(5) + 1);
    }

    public void transferStudent(String fio, String nameOfCurrentGroup, String nameOfDestGroup) {
        allStudents[findStudentByFIO(fio) - 1].enrollInGroup(groups[findGroup(nameOfDestGroup) - 1]);
        groups[findGroup(nameOfCurrentGroup) - 1].exclusionOfStudent(fio);
        groups[findGroup(nameOfDestGroup) - 1].setStudent(allStudents[findStudentByFIO(fio) - 1]);
    }

    public void removeStudent(String fio, String nameOfCurrentGroup) {
        groups[findGroup(nameOfCurrentGroup) - 1].exclusionOfStudent(fio);
        allStudents[findStudentByFIO(fio) - 1].discardGroup();
        // доработать
    }

    public void safeCurrentDataStudentsInFile() {
        Date data = new Date();
        DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT);
        try (FileWriter fw = new FileWriter("Students - " + df.format(data) + ".json")) {
            JSONObject out = new JSONObject();
            JSONArray students = new JSONArray();
            for (int i = 0; i < allStudents.length; i++) {
                JSONObject buf = new JSONObject();
                buf.put("id", allStudents[i].getID());
                buf.put("id_group", allStudents[i].getID_group());
                buf.put("fio", allStudents[i].getFIO());
                students.add(buf);
            }
            out.put("students", students);
            fw.write(out.toJSONString());
            //fw.flush();
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void safeCurrentDataGroupsInFile() {
        Date data = new Date();
        DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT);
        try (FileWriter fw = new FileWriter("Groups - " + df.format(data) + ".json")) {
            JSONObject out = new JSONObject();
            JSONArray[] jgroups = new JSONArray[groups.length + 1];
            for (int i = 0; i < groups.length + 1; i++)
                jgroups[i] = new JSONArray();
            for (int i = 0; i < groups.length; i++) {
                JSONObject buf = new JSONObject();
                buf.put("GroupName", groups[i].getTitle());
                jgroups[0].add(buf);
            }
            out.put("Groups", jgroups[0]);
            for (int i = 0; i < groups.length; i++) {
                for (int j = 0; groups[i].getStudent(j) != null; j++) {
                    JSONObject buf = new JSONObject();
                    buf.put("id", groups[i].getStudent(j).getID());
                    buf.put("fio", groups[i].getStudent(j).getFIO());
                    jgroups[i + 1].add(buf);
                }
                out.put(groups[i].getTitle(), jgroups[i + 1]);
            }
            out.writeJSONString(fw);
            fw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void chooseHeadInGroup(String str) {
        if (!str.equals(""))
            for (int i = 0; i < groups.length; i++)
                if (groups[i].getTitle().equals(str))
                    groups[i].electionHead();
    }

    public void printListOfStudents() {
        for (int i = 0; i < allStudents.length; i++)
            System.out.println(allStudents[i].getID() + " " + allStudents[i].getID_group() + " " + allStudents[i].getFIO());
    }

    public void printListOfGroups() {

    }

    public int findGroup(String name) {
        int i = 0;
        while (groups[i] != null && i < groups.length) {
            if (groups[i].getTitle().equals(name))
                break;
            i++;
        }
        return i + 1;
    }

    public String findStudentByID(int id) {
        if (dNumStudents > 0) {
            int i = 0;
            while (allStudents[i] != null && i < allStudents.length) {
                if (allStudents[i].getID() == id)
                    break;
                i++;
            }
            return allStudents[i].getFIO();
        } else
            return "null";

    }

    public int findStudentByFIO(String fio) {
        if (dNumStudents > 0) {
            int i = 0;
            while (allStudents[i] != null && i < allStudents.length) {
                if (allStudents[i].getFIO().equals(fio))
                    break;
                i++;
            }
            return i + 1;
        } else
            return -1;

    }

    public int getDNumStudents (){
        return dNumStudents;
    }

    public int getdNumGroups () {
        return dNumGroups;
    }

    public Group getGroup (int index) {
        if (index >= 0 && index < groups.length)
            return groups[index];
        return null;
    }

    public Student getStudent(int index) {
        if (index >= 0 && index < allStudents.length)
            return allStudents[index];
        return null;
    }
}

public class Lab3_1 {
    public static void main(String[] args) {
		
    }
}
