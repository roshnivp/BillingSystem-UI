/**
 * Created by Roshni Velluva Puthanidam on 22/10/16.
 */

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.text.DecimalFormat;
import java.util.Set;

public class McPatternsGUI {
    @FXML private TextField cardFirstDigits, cardSecondDigits,cardThirdDigits,cardFourthDigits,cardFifthDigits;
    @FXML private TextField amount;
    @FXML private Text payText;
    @FXML private Button pay,itemRemove,clear,place;
    @FXML private VBox stack;
    @FXML private GridPane gridTable;
    @FXML private ChoiceBox cardType;
    @FXML private ListView<String> list ;
    private Set<String> stringSet;
    ObservableList<String> names;
    private double Total;
    private int firstCellCount=4,secondCellCount=4,thirdCellCount=4,fourthCellCount=4,fifthCellCount=4;
    private String selectedItem;


    CardFactory cf=new CardFactory();
    CreditCard cc;

    McPatternsPresenter presenter;


    @FXML
    void initialize() {
        payText.setText("");
        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // Your action here
                selectedItem=newValue;
                System.out.println("Selected item: " + newValue);

            }
        });

    }
    @FXML
    void checkLengthAndContent(KeyEvent event) {
        System.out.println(event);
        if(cardFirstDigits.getLength()<firstCellCount)
            cardFirstDigits.requestFocus();
        else if(cardSecondDigits.getLength()<secondCellCount)
            cardSecondDigits.requestFocus();
        else if(cardThirdDigits.getLength()<thirdCellCount)
            cardThirdDigits.requestFocus();
        else if(cardFourthDigits.getLength()<fourthCellCount)
            cardFourthDigits.requestFocus();
        else if(cardFifthDigits.getLength()<fifthCellCount)
            cardFifthDigits.requestFocus();
        else pay.requestFocus();

    }
    @FXML
    void checkAndPay(ActionEvent event) {
         int value;
        if(cardFirstDigits.getText().toString().length()==0) {
            pay.setText("Pay");
        }
        cc=cf.createCard(cardType.getValue().toString());
        value=cc.cardVerify(cardFirstDigits.getText().toString(), cardSecondDigits.getText().toString(),cardThirdDigits.getText().toString(),cardFourthDigits.getText().toString(),cardFifthDigits.getText().toString(),cardFirstDigits.getLength(),cardSecondDigits.getLength(), cardThirdDigits.getLength(),cardFourthDigits.getLength(),cardFifthDigits.getLength());

        if(value==1){
                    pay.setText("Paid");
                    pay.setDisable(true);
                    payText.setText("Payment Successful");
        }
        else{
            System.out.println("Invalid Card Number");
            payText.setText("Payment Failed");
        }
    }
    @FXML
    void cardSelect(ActionEvent event){
        System.out.println("->"+event);
        System.out.println(cardType.getValue());
        if(cardType.getValue().equals("VISA")){
            cardFirstDigits.setPromptText("4XXX");
        }
        else if(cardType.getValue().equals("MASTER")){
            cardFirstDigits.setPromptText("5XXX");
        }
        else if(cardType.getValue().equals("DISCOVER")){
            cardFirstDigits.setPromptText("6011");
        }
        else if(cardType.getValue().equals("AMEX")){
            cardFirstDigits.setPromptText("3XXX");
        }
    }

    @FXML
    void clear(ActionEvent event) {
        Total=0;
        amount.clear();
        list.getItems().clear();
        cardFirstDigits.clear();
        cardSecondDigits.clear();
        cardThirdDigits.clear();
        cardFourthDigits.clear();
        cardFifthDigits.clear();
        cardType.setValue("VISA");
        pay.setText("Pay");
        payText.setText("");
        pay.setDisable(true);
        itemRemove.setDisable(true);
        place.setDisable(true);
        selectedItem=null;
    }

    // @FXML
    void addItem(ActionEvent event) {
        Button btn = (Button) event.getSource();
        pay.setDisable(false);
        itemRemove.setDisable(false);
        place.setDisable(false);
        String[] price = btn.getText().toString().split("-");
        Total = Total + Double.parseDouble(price[1]);
        calculateTotal();
        list.getItems().add(btn.getText());
        names = FXCollections.observableArrayList(list.getItems());
        list.setItems(names);
    }

    @FXML
    void loadFile(ActionEvent event){
        readFile();
    }

    private String readFile() {
        presenter =new McPatternsPresenter();
        presenter.loadFile();
        StringBuilder stringBuffer = new StringBuilder();
        //BufferedReader bufferedReader = null;
        ArrayList<String[]> text =presenter.getDetails();

        stack.getChildren().clear();
        String[] label;
        int i = 0;
        for (int j = 0; j < text.size(); j++) {
            stringBuffer.append(text);
            label = text.get(j);
            String line=label[0]+"-"+label[1];
            if (line.length() > 0) {
                Button button = createButton(line);
                gridTable.add(button, i % 3, i / 3);
                // stack.getChildren().add(button);
                i++;
            }
        }

        return stringBuffer.toString();
    }

    private Button createButton(String label) {
        Button newItem = new Button(label);
        newItem.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        newItem.setOnAction(this::addItem);
        return newItem ;
    }

    private void calculateTotal(){
        DecimalFormat twoDecimals = new DecimalFormat("#.##");
        Total = Double.valueOf(twoDecimals.format(Total));
        String formattedTotal = String.format("%.02f", Total);
        amount.setText(formattedTotal);
    }
    @FXML
    void deleteItem(ActionEvent event){
        if(selectedItem!=null)
        {
            String[] price = selectedItem.split("-");
            Total = Total - Double.parseDouble(price[1]);
            System.out.println(price[1]+" Total: "+Total);
            calculateTotal();
            list.getItems().remove(selectedItem);
            itemRemove.isFocused();
            pay.setDisable(false);
            pay.setText("Pay");
        }
    }
    @FXML
    void placeOrder(ActionEvent event){
        System.out.println("Order Placed");
        place.setDisable(true);
    }
}
