/*
Lab 2. Тесты разработанных методов класса AUTOMATA. 21 шт.
*/
package Lab_java;

import junit.framework.TestCase;
import org.junit.*;

public class Lab2_1_Test extends TestCase {

    Automata coffeMashine = new Automata();

    @After
    public void tearDown() {
        coffeMashine.clear();
    }

    @Test
    public void test_Turn_OFF_OFF() {
        coffeMashine.setState("OFF");
        assertEquals("Current State of coffeMashine", "OFF", coffeMashine.getCurrentState());
        assertEquals("OFF->OFF", 1, coffeMashine.off());
        assertEquals("Current State of coffeMashine", "OFF", coffeMashine.getCurrentState());
    }

    @Test
    public void test_Turn_WAIT_OFF() {
        coffeMashine.setState("WAIT");
        assertEquals("Current State of coffeMashine", "WAIT", coffeMashine.getCurrentState());
        assertEquals("WAIT->OFF", 0, coffeMashine.off());
        assertEquals("Current State of coffeMashine", "OFF", coffeMashine.getCurrentState());
    }

    @Test
    public void test_Turn_ACCEPT_OFF() {
        coffeMashine.setState("ACCEPT");
        assertEquals("Current State of coffeMashine", "ACCEPT", coffeMashine.getCurrentState());
        assertEquals("ACCEPT->OFF", 1, coffeMashine.off());
        assertEquals("Current State of coffeMashine", "ACCEPT", coffeMashine.getCurrentState());
    }

    @Test
    public void test_Turn_COOK_OFF() {
        coffeMashine.setState("COOK");
        assertEquals("Current State of coffeMashine", "COOK", coffeMashine.getCurrentState());
        assertEquals("COOK->OFF", 1, coffeMashine.off());
        assertEquals("Current State of coffeMashine", "COOK", coffeMashine.getCurrentState());
    }

    @Test
    public void test_Turn_OFF_ON() {
        coffeMashine.setState("OFF");
        assertEquals("Current State of coffeMashine", "OFF", coffeMashine.getCurrentState());
        assertEquals("OFF->ON", 0, coffeMashine.on());
        assertEquals("Current State of coffeMashine", "WAIT", coffeMashine.getCurrentState());
    }

    @Test
    public void test_Turn_WAIT_ON() {
        coffeMashine.setState("WAIT");
        assertEquals("Current State of coffeMashine", "WAIT", coffeMashine.getCurrentState());
        assertEquals("WAIT->ON", 1, coffeMashine.on());
        assertEquals("Current State of coffeMashine", "WAIT", coffeMashine.getCurrentState());
    }

    @Test
    public void test_Turn_ACCEPT_ON() {
        coffeMashine.setState("ACCEPT");
        assertEquals("Current State of coffeMashine", "ACCEPT", coffeMashine.getCurrentState());
        assertEquals("ACCEPT->ON", 1, coffeMashine.on());
        assertEquals("Current State of coffeMashine", "ACCEPT", coffeMashine.getCurrentState());
    }

    @Test
    public void test_Turn_COOK_ON() {
        coffeMashine.setState("COOK");
        assertEquals("Current State of coffeMashine", "COOK", coffeMashine.getCurrentState());
        assertEquals("COOK->ON", 1, coffeMashine.on());
        assertEquals("Current State of coffeMashine", "COOK", coffeMashine.getCurrentState());
    }

    @Test
    public void test_Coin_Input_States_WAIT() {
        coffeMashine.setState("WAIT");
        assertEquals("Current State of coffeMashine", "WAIT", coffeMashine.getCurrentState());
        assertEquals("ACCEPT 10 rub", 0, coffeMashine.coin(10));
        assertEquals("Total 10 rub", 10, coffeMashine.getCash());
        assertEquals("Current State of coffeMashine", "ACCEPT", coffeMashine.getCurrentState());
    }

    @Test
    public void test_Coin_Input_States_ACCEPT() {
        coffeMashine.setState("ACCEPT");
        assertEquals("Current State of coffeMashine", "ACCEPT", coffeMashine.getCurrentState());
        assertEquals("ACCEPT 5 rub", 0, coffeMashine.coin(5));
        assertEquals("ACCEPT 5 rub", 0, coffeMashine.coin(5));
        assertEquals("Total 10 rub", 10, coffeMashine.getCash());
        assertEquals("Current State of coffeMashine", "ACCEPT", coffeMashine.getCurrentState());
    }

    @Test
    public void test_Coin_Input_NOMINALS() {
        coffeMashine.setState("ACCEPT");
        assertEquals("Current State of coffeMashine", "ACCEPT", coffeMashine.getCurrentState());
        assertEquals("ACCEPT 5 rub", 0, coffeMashine.coin(5));
        assertEquals("ACCEPT 10 rub", 0, coffeMashine.coin(10));
        assertEquals("ACCEPT 50 rub", 0, coffeMashine.coin(50));
        assertEquals("ACCEPT 100 rub", 0, coffeMashine.coin(100));
        assertEquals("ACCEPT 500 rub", 1, coffeMashine.coin(500));
        assertEquals("ACCEPT 1000 rub", 1, coffeMashine.coin(1000));
        assertEquals("Total 165 rub", 165, coffeMashine.getCash());
        assertEquals("Current State of coffeMashine", "ACCEPT", coffeMashine.getCurrentState());
    }

    @Test
    public void test_PrintMenu() {
        coffeMashine.setState("WAIT");
        assertEquals("Current State of coffeMashine", "WAIT", coffeMashine.getCurrentState());
        assertEquals("Print on console menu", 0, coffeMashine.printMenu());
        assertEquals("1st position in the menu", "Coffe",coffeMashine.getMenuPosition(0));
        assertEquals("7th position in the menu", "Sprite",coffeMashine.getMenuPosition(6));
        assertEquals("1st position in prices", 40,coffeMashine.getPricePosition(0));
        assertEquals("7th position in prices", 45,coffeMashine.getPricePosition(6));
        assertEquals("Current State of coffeMashine", "WAIT", coffeMashine.getCurrentState());
    }

    @Test
    public void test_SetChoice() {
        coffeMashine.setState("WAIT");
        coffeMashine.printMenu();
        coffeMashine.coin(100);
        assertEquals("Current State of coffeMashine", "ACCEPT", coffeMashine.getCurrentState());
        assertEquals("Our chioce - 1", 0, coffeMashine.setChoice(1));
        assertEquals("Our chioce - 7", 0, coffeMashine.setChoice(7));
        assertEquals("Our chioce - 10", 1, coffeMashine.setChoice(10));
        assertEquals("Our chioce - (-1)", 1, coffeMashine.setChoice(-1));
        assertEquals("Mast be 7 - our last choise", 7, coffeMashine.getChoise());
        assertEquals("Current State of coffeMashine", "ACCEPT", coffeMashine.getCurrentState());
    }

    @Test
    public void test_check_OK() {
        coffeMashine.on();
        coffeMashine.printMenu();
        coffeMashine.coin(100);
        coffeMashine.setChoice(1);
        assertEquals("Current State of coffeMashine", "ACCEPT", coffeMashine.getCurrentState());
        assertEquals("Prise", 40, coffeMashine.getPricePosition(0));
        assertEquals("Name", "Coffe", coffeMashine.getMenuPosition(0));
        assertEquals("Name", true, coffeMashine.check());
        assertEquals("Current State of coffeMashine", "ACCEPT", coffeMashine.getCurrentState());
    }

    @Test
    public void test_check_ERROR() {
        coffeMashine.on();
        coffeMashine.printMenu();
        coffeMashine.coin(10);
        coffeMashine.setChoice(1);
        assertEquals("Current State of coffeMashine", "ACCEPT", coffeMashine.getCurrentState());
        assertEquals("Prise", 40, coffeMashine.getPricePosition(0));
        assertEquals("Name", "Coffe", coffeMashine.getMenuPosition(0));
        assertEquals("Name", false, coffeMashine.check());
        assertEquals("Current State of coffeMashine", "ACCEPT", coffeMashine.getCurrentState());
    }

    @Test
    public void test_Cansel_Session_State_ACCEPT(){
        coffeMashine.on();
        coffeMashine.printMenu();
        coffeMashine.coin(10);
        coffeMashine.setChoice(1);
        assertEquals("Current State of coffeMashine", "ACCEPT", coffeMashine.getCurrentState());
        assertEquals("Cansel current session", 0, coffeMashine.cancel());
        assertEquals("Cash", 0, coffeMashine.getCash());
        assertEquals("Choice", 0, coffeMashine.getChoise());
        assertEquals("Current State of coffeMashine", "WAIT", coffeMashine.getCurrentState());
    }

    @Test
    public void test_Cansel_Session_State_WAIT(){
        coffeMashine.on();
        assertEquals("Current State of coffeMashine", "WAIT", coffeMashine.getCurrentState());
        assertEquals("Cansel current session", 1, coffeMashine.cancel());
        assertEquals("Current State of coffeMashine", "WAIT", coffeMashine.getCurrentState());
    }

    @Test
    public void test_COOK_OK(){
        coffeMashine.on();
        coffeMashine.printMenu();
        coffeMashine.coin(50);
        coffeMashine.setChoice(1);
        assertEquals("Current State of coffeMashine", "ACCEPT", coffeMashine.getCurrentState());
        assertEquals("Cook ....... OK", 0, coffeMashine.cook());
        assertEquals("Current change", 10, coffeMashine.getChange());
        assertEquals("Current State of coffeMashine", "COOK", coffeMashine.getCurrentState());
    }

    @Test
    public void test_COOK_ERROR(){
        coffeMashine.on();
        coffeMashine.printMenu();
        coffeMashine.coin(10);
        coffeMashine.setChoice(1);
        assertEquals("Current State of coffeMashine", "ACCEPT", coffeMashine.getCurrentState());
        assertEquals("Cook ERROR", 1, coffeMashine.cook());
        assertEquals("Current State of coffeMashine after cook", "ACCEPT", coffeMashine.getCurrentState());
    }

    @Test
    public void test_Finish_OK(){
        coffeMashine.on();
        coffeMashine.printMenu();
        coffeMashine.coin(100);
        coffeMashine.setChoice(4);
        coffeMashine.cook();
        assertEquals("Current State of coffeMashine", "COOK", coffeMashine.getCurrentState());
        assertEquals("Change before finish", 50, coffeMashine.getChange());
        assertEquals("Finish OK", 0, coffeMashine.finish());
        assertEquals("AllMoney after finish", 1050, coffeMashine.getAllMoney());
        assertEquals("Change after finish", 0, coffeMashine.getChange());
        assertEquals("Choise after finish", 0, coffeMashine.getChoise());
        assertEquals("Cash after finish", 0, coffeMashine.getCash());
        assertEquals("Current State of coffeMashine", "WAIT", coffeMashine.getCurrentState());
    }

    @Test
    public void test_Finish_ERROR(){
        coffeMashine.setState("ACCEPT");
        assertEquals("Current State of coffeMashine", "ACCEPT", coffeMashine.getCurrentState());
        assertEquals("Finish ERROR", 1, coffeMashine.finish());
        assertEquals("Current State of coffeMashine", "ACCEPT", coffeMashine.getCurrentState());
    }
}
