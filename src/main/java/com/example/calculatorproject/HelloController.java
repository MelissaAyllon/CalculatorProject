package com.example.calculatorproject;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    private Label welcomeText, lblResult;
    @FXML
    private Pane titlePane, btn0, btn1, btn2, btn3,
                btn4, btn5, btn6, btn7, btn8, btn9;
    @FXML
    private Pane btnC, btnDivi, btnEqua,
                btnMult, btnSub, btnSum;
    @FXML
    private ImageView cerrar;
    @FXML
    private ImageView minimizar;
    private double x;
    private double y;
    private String operator = ".";
    private double num1;

    protected void init(Stage stage) {

        /**
         * this method is a lambda expression
         * when u pressed the object
         * automatically is going to get the scene coordinate
         **/
        titlePane.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        titlePane.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });

        cerrar.setOnMouseClicked(event -> {
            stage.close();
        });

        minimizar.setOnMouseClicked(event -> {
            stage.setIconified(true);
        });
    }

    @FXML
    void onNumberClicked(MouseEvent event) {
        int value = Integer.parseInt(((Pane)event.getSource()).getId().replace("btn",""));
        lblResult.setText(Double.parseDouble(lblResult.getText())==0?String.valueOf((double)value):String.valueOf(Double.parseDouble(lblResult.getText())*10+value));
    }

    @FXML
    void onSymbolClicked(MouseEvent event) {
        String symbol = ((Pane)event.getSource()).getId().replace("btn","");
        if (symbol.equals("Equa")){
            double num2= Double.parseDouble(lblResult.getText());
            switch (operator){
                case "+" -> lblResult.setText((num1 + num2) + "");
                case "-" -> lblResult.setText((num1 - num2) + "");
                case "*" -> lblResult.setText((num1 * num2) + "");
                case "/" -> lblResult.setText((num1 / num2) + "");
            }
        }
        else if(symbol.equals("C")){
            lblResult.setText(String.valueOf(0.0));
            operator = ".";
        }
        else{
            switch (symbol){
                case "Sum" -> operator = "+";
                case "Sub" -> operator = "-";
                case "Mult" -> operator = "*";
                case "Divi" -> operator = "/";
            }
            num1 = Double.parseDouble(lblResult.getText());
            lblResult.setText(String.valueOf(0.0));
        }
    }
}