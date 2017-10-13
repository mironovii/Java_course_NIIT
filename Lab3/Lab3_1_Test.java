/*
Lab 3. Тесты разработанных методов класса Dekanat. 11 шт.
*/
package Lab_java;

import junit.framework.TestCase;
import org.junit.*;

import java.io.File;

public class Lab3_1_Test extends TestCase {
    Dekanat dk = new Dekanat();
    File f1 = new File("Students.json");
    File f2 = new File("Groups.json");

    @Test
    public void testfillStudents() {
        dk.fillStudents(f1);
        for (int i = 0; i < dk.getDNumStudents(); i++) {
            assertEquals("Test of correct ID", i + 1, dk.getStudent(i).getID());
        }
    }

    @Test
    public void testfillGroups() {
        dk.fillStudents(f1);
        dk.fillGroups(f2);
        for (int i = 0; i < dk.getdNumGroups(); i++) {
            assertEquals("Test of correct Groups", "NIIT-0" + (i + 1), dk.getGroup(i).getTitle());
        }
    }

    @Test
    public void testTransferStudent() {
        dk.fillStudents(f1);
        dk.fillGroups(f2);
        assertEquals("NIIT-01", dk.getStudent(dk.findStudentByFIO("Иванов А.А.") - 1).getID_group());
        dk.transferStudent("Иванов А.А.", "NIIT-01", "NIIT-03");
        assertEquals("NIIT-03", dk.getStudent(dk.findStudentByFIO("Иванов А.А.") - 1).getID_group());

    }

    @Test
    public void testChooseHeadInGroup() {
        dk.fillStudents(f1);
        dk.fillGroups(f2);
        assertEquals(null, dk.getGroup(1).getHead());
        dk.chooseHeadInGroup("NIIT-02");
        for (int i = 0; i < dk.getDNumStudents(); i++) {
            if (dk.getStudent(i).getFIO().equals(dk.getGroup(1).getHead().getFIO())) {
                assertEquals(dk.getStudent(i).getFIO(), dk.getGroup(1).getHead().getFIO());
                assertEquals("NIIT-02", dk.getStudent(i).getID_group());
                break;
            }
        }
    }

    @Test
    public void testRemoveStudent() {
        dk.fillStudents(f1);
        dk.fillGroups(f2);
        assertEquals("NIIT-01",dk.getStudent(dk.findStudentByFIO("Волков Р.Р.")-1).getID_group());
        dk.removeStudent("Волков Р.Р.","NIIT-01");
        assertEquals("null",dk.getStudent(dk.findStudentByFIO("Волков Р.Р.")-1).getID_group());
    }

    @Test
    public void testGetDNumGroups() {
        dk.fillStudents(f1);
        dk.fillGroups(f2);
        assertEquals(3,dk.getdNumGroups());
        }

    @Test
    public void testGetDNumStudents() {
        dk.fillStudents(f1);
        dk.fillGroups(f2);
        assertEquals(30,dk.getDNumStudents());
    }

    @Test
    public void testFindStudentByFIO() {
        dk.fillStudents(f1);
        dk.fillGroups(f2);
        assertEquals("ID in list",28,dk.findStudentByFIO("Макаров В.В."));
    }

    @Test
    public void testFindStudentByID() {
        dk.fillStudents(f1);
        dk.fillGroups(f2);
        assertEquals("FIO in list","Макаров В.В.",dk.findStudentByID(28));
    }

    @Test
    public void testGenRndMarks() {
        dk.fillStudents(f1);
        dk.fillGroups(f2);
        assertEquals(-1,dk.getStudent(0).getNumMarks());
        dk.genRndMarks();
        assertEquals(50,dk.getStudent(0).getNumMarks());
    }

    @Test
    public void testFindGroup() {
        dk.fillStudents(f1);
        dk.fillGroups(f2);
        assertEquals("Index in list",3,dk.findGroup("NIIT-03"));
    }

}
