/*
Lab2. Разработать класс AUTOMATA, являющийся упрощенной моделью автомата по продаже горячих напитков.
*/

package Lab_java;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Thread;

class Automata{
    public enum STATES{
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

    public int on(){
        if( state == STATES.OFF){
            state = STATES.WAIT;
            return 0;
        }
        else
            return 1;
    }
    public int off(){
        if( state == STATES.WAIT){
            state = STATES.OFF;
            return 0;
        }
        else
            return 1;
    }
    public int coin(int coin){
        if ((coin == 50 || coin == 100 || coin == 10 || coin == 5)
                && (state == STATES.WAIT || state == STATES.ACCEPT)){
            cash += coin;
            if(state != STATES.ACCEPT)
                state = STATES.ACCEPT;
            return 0;
        }
        else
            return 1;
    }
    public int printMenu(){
        if(state == STATES.WAIT){
            if(prices == null || menu == null){
                String[] buf;
                int i = 0;
                try(BufferedReader fp = new BufferedReader(new FileReader("Menu.csv"))) {
                    fp.mark(1);
                    while(fp.ready()){
                        fp.readLine();
                        i++;
                    }
                    fp.reset();
                    menu = new String[i];
                    prices = new int[i];
                    i = 0;
                    while(fp.ready()){
                        buf = fp.readLine().split(",");
                        menu[i] = buf[0];
                        prices[i] = Integer.parseInt(buf[1]);
                        System.out.println((i+1) + " " + menu[i] + " \t" + prices[i] + " rub");
                        i++;
                    }
                }
                catch(IOException e){
                    e.getMessage();
                    e.printStackTrace();
                }
                return 0;
            }
            else{
                int i = 0;
                while(i < prices.length){
                    System.out.println((i+1) + " " + menu[i] + " \t" + prices[i] + " rub");
                    i++;
                }
                return 0;
            }
        }
        else
            return 1;
    }
    public String getCurrentState(){
        return state.toString();
    }
    public int setChoice(int n){
        if((n > 0) && (n <= prices.length) && state == STATES.ACCEPT){
            choice = n;
            return 0;
        }
        else
            return 1;
    }
    public boolean check(){
        if(choice != 0 && cash >= prices[choice] && state == STATES.ACCEPT){
            return true;
        }
        else
            return false;
    }
    public int cancel(){
        if(state == STATES.ACCEPT){
            getChange(cash);
            cash = 0;
            choice = 0;
            state = STATES.WAIT;
            return 0;
        }
        else
            return 1;
    }
    public int cook(){
        if(check()){
            state = STATES.COOK;
            change = cash - prices[choice-1];
            try{
                Thread.sleep(10000);
            }
            catch(Exception e){
                e.printStackTrace();
            }
            //finish();
            return 0;
        }
        else
            return 1;
    }
    public int finish(){
        if(state == STATES.COOK){
        getChange(change);
        allMoney += prices[choice-1];
        cash = 0;
        choice = 0;
        change = 0;
        state = STATES.WAIT;
        return 0;
        }
        else
            return 1;
    }
    public int getChange (int changeCust){
        return changeCust;
    }

    // fields below assist in unit testing
    public void setState (String str){
        switch(str){
            case "OFF":
                state = STATES.OFF;
                break;
            case "WAIT":
                state = STATES.WAIT;
                break;
            case "ACCEPT":
                state = STATES.ACCEPT;
                break;
            case "COOK":
                state = STATES.COOK;
                break;
            default:
                break;
        }
    }
    public int getAllMoney(){
        return allMoney;
    }
    public int getCash(){
        return cash;
    }
    public int getChoise(){
        return choice;
    }
    public void clear(){
        allMoney = 1000;
        cash = 0;
        menu = null;
        prices = null;
        state = STATES.OFF;
        choice = 0;
    }
    public String getMenuPosition(int i){
        return menu[i];
    }
    public int getPricePosition(int i){
        return prices[i];
    }
    public int getChange(){
        return change;
    }
}

public class Lab2_1 {
    public static void main(String[] args) {

    }
}

