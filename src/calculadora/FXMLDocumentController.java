/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package calculadora;

import static java.lang.Integer.sum;
import java.lang.reflect.Array;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author Aldenir
 */
public class FXMLDocumentController implements Initializable {
    
    
    @FXML
    private Button bt3;
    @FXML
    private Button bt2;
    @FXML
    private Button bt5;
    @FXML
    private Button bt4;
    @FXML
    private Button bt7;
    @FXML
    private Button bt6;
    @FXML
    private Button bt9;
    @FXML
    private Button bt8;
    @FXML
    private Button bt1;
    @FXML
    private Button bt0;
    @FXML
    private Button btPlus;
    @FXML
    private Button btDot;
    @FXML
    private Label display;
    @FXML
    private Label labelOperation;
    @FXML
    private Label labelTotal;
    @FXML
    private Button btClear;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println(event.getTarget());
        ArrayList<String> dpValues = displayCheck();
        
        String textLb = dpValues.get(0);
        String textDp = dpValues.get(1);
        
        switch (event.getTarget().toString()){
            case "Button[id=btPlus, styleClass=button]'+'":
                setValueDisplay(textLb, textDp, "+");
                break;
                
            case "Button[id=btMin, styleClass=button]'-'":
                setValueDisplay(textLb, textDp, "-");
                break;
            
            case "Button[id=btMul, styleClass=button]'X'":
                setValueDisplay(textLb, textDp, "*");
                break;
            
            case "Button[id=btDiv, styleClass=button]'/'":
                setValueDisplay(textLb, textDp, "/");
                break;
            
            case "Button[id=btEval, styleClass=button]'='":
                labelOperation.setText(textLb+textDp);
                String result = concat(labelOperation.getText());
                display.setText("="+result);
                labelTotal.setText(result);
                break;
            case "Button[id=btClear, styleClass=button]'C'":
                labelOperation.setText("0");
                display.setText("0");
                labelTotal.setText("=000");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bt0.setOnAction(e -> catchValueButton(bt0));
        bt1.setOnAction(e -> catchValueButton(bt1));
        bt2.setOnAction(e -> catchValueButton(bt2));
        bt3.setOnAction(e -> catchValueButton(bt3));
        bt4.setOnAction(e -> catchValueButton(bt4));
        bt5.setOnAction(e -> catchValueButton(bt5));
        bt6.setOnAction(e -> catchValueButton(bt6));
        bt7.setOnAction(e -> catchValueButton(bt7));
        bt8.setOnAction(e -> catchValueButton(bt8));
        bt9.setOnAction(e -> catchValueButton(bt9));
        btDot.setOnAction(e -> catchValueButton(btDot));
    }
    
    private ArrayList displayCheck(){
        ArrayList<String> temp = new ArrayList(Arrays.asList("", ""));
        
        if (display.getText().contains("=")){
            String newStr = display.getText().substring(1, display.getText().length());
            temp.set(1, newStr);
            
            labelOperation.setText(newStr);
            display.setText("");
        }else{
            if (!display.getText().equals("0")){
                temp.set(1, display.getText());
            }
            if(!labelOperation.getText().equals("0")){
                temp.set(0, labelOperation.getText());
            }
        }
        System.out.println("No Display: "+temp.toString()+"\n");
        return temp;
    }
    
    private void catchValueButton(Button button){
        if (!display.getText().equals("0")){
            display.setText(display.getText()+button.getText());
        }else{
            display.setText("");
            display.setText(display.getText()+button.getText());
        }
    }
    
    private void setValueDisplay(String lbtext, String dpText, String operText){
        labelOperation.setText(lbtext+dpText+operText);
        display.setText("");
    }
    
    private int sumValues(ArrayList<Integer> values){
        int value = 0;
        for (int i : values){
            value += i;
        }
        return value;
    }
    
    private String concat(String expressao){
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js"); // Usando o mecanismo de script JavaScript
        Object resultado = null;
        
        try {
            // Avalie a expressão usando o mecanismo de script
            resultado = engine.eval(expressao);
            // Imprima o resultado
            
        }catch (ScriptException e) {
            // Trate exceções, se houverem
            e.printStackTrace();
            }
        return resultado.toString();
        }
}
