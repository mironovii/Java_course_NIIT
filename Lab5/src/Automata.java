package Lab5_1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Automata {
    public enum STATES {
        OFF,
        WAIT,
        ACCEPT,
        COOK
    }

    private int allMoney = 1000;
    private int cash = 0;
    private String[] menu;
    private int[] prices;
    private STATES state = STATES.OFF;
    private int choice = 0;
    private int change;

    public int on() {
        if (state == STATES.OFF) {
            state = STATES.WAIT;
            return 0;
        } else
            return 1;
    }

    public int off() {
        if (state == STATES.WAIT) {
            state = STATES.OFF;
            return 0;
        } else
            return 1;
    }

    public int coin(int coin) {
        if ((coin == 50 || coin == 100 || coin == 10 || coin == 5)
                && (state == STATES.WAIT || state == STATES.ACCEPT)) {
            cash += coin;
            if (state != STATES.ACCEPT)
                state = STATES.ACCEPT;
            return 0;
        } else
            return 1;
    }

    public int printMenu() {
        if (state == STATES.WAIT) {
            if (prices == null || menu == null) {
                String[] buf;
                int i = 0;
                try (BufferedReader fp = new BufferedReader(new FileReader("Menu.csv"))) {
                    fp.mark(1);
                    while (fp.ready()) {
                        fp.readLine();
                        i++;
                    }
                    fp.reset();
                    menu = new String[i];
                    prices = new int[i];
                    i = 0;
                    while (fp.ready()) {
                        buf = fp.readLine().split(",");
                        menu[i] = buf[0];
                        prices[i] = Integer.parseInt(buf[1]);
                        //System.out.println((i + 1) + " " + menu[i] + " " + prices[i] + " rub");
                        i++;
                    }
                } catch (IOException e) {
                    e.getMessage();
                    e.printStackTrace();
                }
                return 0;
            } else {
                int i = 0;
                while (i < prices.length) {
                    //System.out.println((i + 1) + " " + menu[i] + " " + prices[i] + " rub");
                    i++;
                }
                return 0;
            }
        } else
            return 1;
    }

    public String getCurrentState() {
        return state.toString();
    }

    public int setChoice(int n) {
        if ((n > 0) && (n <= prices.length) && state == STATES.ACCEPT) {
            choice = n;
            return 0;
        } else
            return 1;
    }

    public boolean check() {
        if (choice != 0 && cash >= prices[choice] && state == STATES.ACCEPT) {
            return true;
        } else
            return false;
    }

    public int cancel() {
        if (state == STATES.ACCEPT) {
            getChange();
            cash = 0;
            choice = 0;
            state = STATES.WAIT;
            return 0;
        } else
            return 1;
    }

    public int cook() {
        if (check()) {
            state = STATES.COOK;
            change = cash - prices[choice - 1];
            finish();
            return 0;
        } else
            return 1;
    }

    private int finish() {
        if (state == STATES.COOK) {
            getChange();
            allMoney += prices[choice - 1];
            cash = 0;
            choice = 0;
            //change = 0;
            state = STATES.WAIT;
            return 0;
        } else
            return 1;
    }

    public int getChange() {
        return change;
    }

    public String getMenu(int n) {
        if (n > 0 && (n-1) < menu.length)
            return menu[n-1];
        else
            return null;
    }

    public int getPrices(int n) {
        if (n > 0 && (n-1) < prices.length)
            return prices[n-1];
        else
            return -1;
    }

    public int getCash (){
        return cash;
    }
}
