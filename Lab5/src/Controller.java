package Lab5_1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.concurrent.Task;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Controller {
    Automata automata = new Automata();
    Task taskCook;

    @FXML
    private Button but_on_off;
    public Button but_deposit;
    public Button but_n1;
    public Button but_n2;
    public Button but_n3;
    public Button but_n4;
    public Button but_n5;
    public Button but_n6;
    public Button but_n7;
    public Button but_n8;
    public Button but_n9;
    public Button but_n0;
    public Button but_C;
    public Button but_E;

    public TextField TextBox2;
    public ProgressBar ProgressBar1;

    public Label Label_state;
    public Label Label_choice;
    public Label Label_price;
    public Label Label_deposit;
    public Label Label_change;
    public Label Label1;
    public Label Label2;
    public Label Label3;
    public Label Label4;
    public Label Label5;
    public Label Label6;

    public ImageView ImageView1;
    public ImageView ImageView2;
    public ImageView ImageView3;
    public ImageView ImageView4;
    public ImageView ImageView5;
    public ImageView ImageView6;

    @FXML
    public void initialize() {
        ButtonSetDisable(true);

        but_on_off.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    if (automata.getCurrentState().equals("OFF")) {
                        but_on_off.setText("OFF");
                        but_on_off.setStyle("-fx-background-color: #ff4f00;");
                        Label_state.setText("Автомат включен.");
                        Label_state.setStyle("-fx-background-color: #50c878;");
                        automata.on();
                        automata.printMenu();
                        ButtonSetDisable(false);
                        setImagesField(true);
                        LoadMenu(true);
                    } else if (automata.getCurrentState().equals("ACCEPT")) {
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Take your money back before you turn off the machine!");
                        alert.setTitle("Warning!");
                        alert.setHeaderText("Hey, ");
                        alert.showAndWait();
                    } else if ( ((taskCook == null) || (taskCook.isRunning() == false))) {
                        but_on_off.setText("ON");
                        but_on_off.setStyle("-fx-background-color: #50c878;");
                        Label_state.setText("Автомат выключен.");
                        Label_state.setStyle("-fx-background-color: #ff4f00;");
                        automata.off();
                        ButtonSetDisable(true);
                        setImagesField(false);
                        LoadMenu(false);
                        Label_choice.setText("");
                        Label_price.setText("");
                        Label_change.setText("");
                        Label_deposit.setText("");
                        TextBox2.setText("");
                    }
                } catch (Exception ex) {
                    System.out.println("Uppps");
                }
            }
        });

        but_n1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label_change.setText("");
                Label_choice.setText(Label_choice.getText() + "1");
                if (automata.getMenu(Integer.parseInt(Label_choice.getText())) != null) {
                    Label_price.setText(automata.getPrices(Integer.parseInt(Label_choice.getText())) + "rub");
                } else {
                    Label_price.setText("");
                }
            }
        });
        but_n2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label_change.setText("");
                Label_choice.setText(Label_choice.getText() + "2");
                if (automata.getMenu(Integer.parseInt(Label_choice.getText())) != null) {
                    Label_price.setText(automata.getPrices(Integer.parseInt(Label_choice.getText())) + "rub");
                } else {
                    Label_price.setText("");
                }
            }
        });
        but_n3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label_change.setText("");
                Label_choice.setText(Label_choice.getText() + "3");
                if (automata.getMenu(Integer.parseInt(Label_choice.getText())) != null) {
                    Label_price.setText(automata.getPrices(Integer.parseInt(Label_choice.getText())) + "rub");
                } else {
                    Label_price.setText("");
                }
            }
        });
        but_n4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label_change.setText("");
                Label_choice.setText(Label_choice.getText() + "4");
                if (automata.getMenu(Integer.parseInt(Label_choice.getText())) != null) {
                    Label_price.setText(automata.getPrices(Integer.parseInt(Label_choice.getText())) + "rub");
                } else {
                    Label_price.setText("");
                }
            }
        });
        but_n5.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label_change.setText("");
                Label_choice.setText(Label_choice.getText() + "5");
                if (automata.getMenu(Integer.parseInt(Label_choice.getText())) != null) {
                    Label_price.setText(automata.getPrices(Integer.parseInt(Label_choice.getText())) + "rub");
                } else {
                    Label_price.setText("");
                }
            }
        });
        but_n6.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label_change.setText("");
                Label_choice.setText(Label_choice.getText() + "6");
                if (automata.getMenu(Integer.parseInt(Label_choice.getText())) != null) {
                    Label_price.setText(automata.getPrices(Integer.parseInt(Label_choice.getText())) + "rub");
                } else {
                    Label_price.setText("");
                }
            }
        });
        but_n7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label_change.setText("");
                Label_choice.setText(Label_choice.getText() + "7");
                if (automata.getMenu(Integer.parseInt(Label_choice.getText())) != null) {
                    Label_price.setText(automata.getPrices(Integer.parseInt(Label_choice.getText())) + "rub");
                } else {
                    Label_price.setText("");
                }
            }
        });
        but_n8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label_change.setText("");
                Label_choice.setText(Label_choice.getText() + "8");
                if (automata.getMenu(Integer.parseInt(Label_choice.getText())) != null) {
                    Label_price.setText(automata.getPrices(Integer.parseInt(Label_choice.getText())) + "rub");
                } else {
                    Label_price.setText("");
                }
            }
        });
        but_n9.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label_change.setText("");
                Label_choice.setText(Label_choice.getText() + "9");
                if (automata.getMenu(Integer.parseInt(Label_choice.getText())) != null) {
                    Label_price.setText(automata.getPrices(Integer.parseInt(Label_choice.getText())) + "rub");
                } else {
                    Label_price.setText("");
                }
            }
        });
        but_n0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label_change.setText("");
                Label_choice.setText(Label_choice.getText() + "0");
                if (automata.getMenu(Integer.parseInt(Label_choice.getText())) != null) {
                    Label_price.setText(automata.getPrices(Integer.parseInt(Label_choice.getText())) + "rub");
                } else {
                    Label_price.setText("");
                }
            }
        });

        but_C.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if ((taskCook == null) || (taskCook.isRunning() == false)) {
                    automata.cancel();
                    Label_choice.setText("");
                    Label_price.setText("");
                    Label_change.setText(Label_deposit.getText());
                    Label_deposit.setText("");
                    TextBox2.setText("");
                }
            }
        });

        but_deposit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!TextBox2.getText().equals("") && automata.coin(Integer.parseInt(TextBox2.getText())) == 0) {
                    Label_deposit.setText(automata.getCash() + "");
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Our machine accepts coins and banknotes with a value of only 5, 10, 50, 100 rub. Or you entered empty field.");
                    alert.setTitle("Information");
                    alert.setHeaderText("Sorry, ");
                    alert.showAndWait();
                    TextBox2.setText("");
                }
            }
        });

        //TextBox2.setOnAction(event -> automata.coin(Integer.parseInt(TextBox2.getText())));

        but_E.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (Label_choice.getText() != "" && automata.setChoice(Integer.parseInt(Label_choice.getText())) == 0 && ((taskCook == null) || (taskCook.isRunning() == false))) {
                    if (automata.cook() == 0) {
                        Label_change.setText(automata.getChange() + "");
                        if (taskCook != null) {
                            taskCook.cancel(true);
                            ProgressBar1.progressProperty().unbind();
                        }
                        taskCook = createWorker();
                        ProgressBar1.setProgress(0);
                        ProgressBar1.progressProperty().unbind();
                        ProgressBar1.progressProperty().bind(taskCook.progressProperty());
                        new Thread(taskCook).start();
                        Label_choice.setText("");
                        Label_price.setText("");
                        Label_deposit.setText("");
                        TextBox2.setText("");
                    }
                }
            }
        });
    }

    public Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i = 0; i < 100; i++) {
                    Thread.sleep(50);
                    updateProgress(i + 1, 100);
                }

                Thread.sleep(100);
                updateProgress(0, 100);
                return true;
            }
        };
    }

    public void ButtonSetDisable(boolean var) {
        TextBox2.setDisable(var);
        but_n1.setDisable(var);
        but_n2.setDisable(var);
        but_n3.setDisable(var);
        but_n4.setDisable(var);
        but_n5.setDisable(var);
        but_n6.setDisable(var);
        but_n7.setDisable(var);
        but_n8.setDisable(var);
        but_n9.setDisable(var);
        but_n0.setDisable(var);
        but_C.setDisable(var);
        but_E.setDisable(var);
        but_deposit.setDisable(var);
    }

    public void setImagesField(boolean var) throws FileNotFoundException {
        if (var) {
            ImageView1.setImage(new Image(new FileInputStream("Img/1.jpg")));
            ImageView2.setImage(new Image(new FileInputStream("Img/2.jpg")));
            ImageView3.setImage(new Image(new FileInputStream("Img/3.jpg")));
            ImageView4.setImage(new Image(new FileInputStream("Img/4.jpg")));
            ImageView5.setImage(new Image(new FileInputStream("Img/5.jpg")));
            ImageView6.setImage(new Image(new FileInputStream("Img/6.jpg")));
            ImageView1.setVisible(true);
            ImageView2.setVisible(true);
            ImageView3.setVisible(true);
            ImageView4.setVisible(true);
            ImageView5.setVisible(true);
            ImageView6.setVisible(true);
        } else {
            ImageView1.setVisible(false);
            ImageView2.setVisible(false);
            ImageView3.setVisible(false);
            ImageView4.setVisible(false);
            ImageView5.setVisible(false);
            ImageView6.setVisible(false);
        }
    }

    public void LoadMenu(boolean var) {
        if (var) {
            Label1.setText(automata.getMenu(1) + " " + automata.getPrices(1) + " rub.");
            Label2.setText(automata.getMenu(2) + " " + automata.getPrices(2) + " rub.");
            Label3.setText(automata.getMenu(3) + " " + automata.getPrices(3) + " rub.");
            Label4.setText(automata.getMenu(4) + " " + automata.getPrices(4) + " rub.");
            Label5.setText(automata.getMenu(5) + " " + automata.getPrices(5) + " rub.");
            Label6.setText(automata.getMenu(6) + " " + automata.getPrices(6) + " rub.");
        } else {
            Label1.setText("");
            Label2.setText("");
            Label3.setText("");
            Label4.setText("");
            Label5.setText("");
            Label6.setText("");
        }
    }
}

