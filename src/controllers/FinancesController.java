package controllers;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import models.Bank;
import models.Player;
import models.StockCorporation;
import models.StockMarket;

/**
 * @author Alex Poole
 */
public class FinancesController implements Initializable {
    
    @FXML
    private AnchorPane anchor;
    
    private Bank myBank;
    
    private Group menu;
    private Group bankOptions;
    private Group depositOptions;
    private Group withdrawOptions;
    private Group loanOptions;
    private Group stockMarketOptions;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Player player = GameController.getGameData().getPlayer();
        myBank = GameController.getGameData().getBank();
        
        //********************Menu**********************//
        
        menu = new Group();
        Button bankButton = new Button("Bank");
        Button stockMarket = new Button("Stock Market");
        Button loanButton = new Button("Apply for Loan");
        Button returnButton = new Button("Return to Space Port");
        
        menu.getChildren().addAll(bankButton, stockMarket, loanButton, returnButton);
        int y = 50;
        
        for (Node n : menu.getChildren()) {
            ((Button) n).setMinSize(200, 50);
            ((Button) n).setTranslateX(200);
            ((Button) n).setTranslateY(y);
            y += 75;
        }
        
        bankButton.setOnMouseClicked((MouseEvent t) -> {
            startBank(menu);
        });
        
        stockMarket.setOnMouseClicked((MouseEvent t) -> {
            startStockMarket(menu);
        });
        
        loanButton.setOnMouseClicked((MouseEvent t) -> {
            startLoan(menu);
        });
        
        returnButton.setOnMouseClicked((MouseEvent t) -> {
            GameController.getControl().setScreen(Screens.NEW_SPACE_PORT);
        });
        
        anchor.getChildren().add(menu);
        
        //******************Deposit/Withdraw Menu****************//
        
        
        bankOptions = new Group();
        Button deposit = new Button("Deposit");
        Button withdraw = new Button("Withdraw");
        Button backToMenu = new Button("Back");
        
        deposit.setMinSize(200, 200);
        withdraw.setMinSize(200, 200);
        deposit.setTranslateX(75);
        deposit.setTranslateY(100);
        withdraw.setTranslateX(325);
        withdraw.setTranslateY(100);
        backToMenu.setMinSize(100, 50);
        backToMenu.setTranslateX(15);
        backToMenu.setTranslateY(335);
        
        deposit.setOnMouseClicked((MouseEvent t) -> {
            startDeposit();
        });
        
        withdraw.setOnMouseClicked((MouseEvent t) -> {
            startWithdraw();
        });
        
        backToMenu.setOnMouseClicked((MouseEvent t) -> {
            startMenu(bankOptions);
        });
        
        bankOptions.getChildren().addAll(deposit, withdraw, backToMenu);
        bankOptions.setOpacity(0.0);
        bankOptions.setMouseTransparent(true);
        anchor.getChildren().add(bankOptions);
        
        //******************Deposit***************//
        //*******************Withdraw**************//
        
        
        depositOptions = new Group();
        
        Label currentCredits = new Label("Player Credits: " + player.getCredits());
        Label bankCredits = new Label("Bank Credits: " + myBank.getCredits());
        Label currentCredits2 = new Label("Player Credits: " + player.getCredits());
        Label bankCredits2 = new Label("Bank Credits: " + myBank.getCredits());
        
        TextField amountDep = new TextField();
        TextField amountWith = new TextField();
        
        Button depositButton = new Button("Deposit");
        Button withdrawButton = new Button("Withdraw");
        depositButton.setDisable(true);
        depositButton.setMinSize(200, 50);
        depositButton.setTranslateX(200);
        depositButton.setTranslateY(250);
        depositButton.setOnMouseClicked((MouseEvent t) -> {
            myBank.deposit(Integer.parseInt(amountDep.getText()));
            player.spend(Integer.parseInt(amountDep.getText()));
            currentCredits.setText("Player Credits: " + player.getCredits());
            bankCredits.setText("Bank Credits: " + myBank.getCredits());
            currentCredits2.setText("Player Credits: " + player.getCredits());
            bankCredits2.setText("Bank Credits: " + myBank.getCredits());
            amountWith.setText("");
            withdrawButton.setDisable(true);
            amountDep.setText("");
            depositButton.setDisable(true);
        });
        
        amountDep.addEventFilter(KeyEvent.KEY_TYPED, (KeyEvent event) -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume();
            }
        });
        
        amountDep.setOnKeyReleased((KeyEvent e) -> {
            if (amountDep.getText().isEmpty() || (!amountDep.getText().isEmpty() && Integer.parseInt(amountDep.getText()) > player.getCredits())) {
                depositButton.setDisable(true);
            } else {
                depositButton.setDisable(false);
            }
        });

        Label depositLabel = new Label("Deposit: ");
        depositLabel.setMinSize(100, 30);
        currentCredits.setAlignment(Pos.CENTER);
        bankCredits.setAlignment(Pos.CENTER);
        depositLabel.setAlignment(Pos.CENTER);
        currentCredits.setMinSize(200, 30);
        bankCredits.setMinSize(200, 30);
        amountDep.setMinSize(100, 30);
        currentCredits.setTranslateX(200);
        currentCredits.setTranslateY(70);
        bankCredits.setTranslateX(200);
        bankCredits.setTranslateY(210);
        amountDep.setTranslateX(250);
        amountDep.setTranslateY(140);
        amountDep.setStyle("-fx-border-color: #FFFFFF;\n" +
                "-fx-background-color:#000000;\n" +
                "-fx-text-fill: #FFFFFF;\n" +
                "-fx-border-radius: 10;\n" +
                "-fx-background-radius: 10;");
        depositLabel.setTranslateX(150);
        depositLabel.setTranslateY(140);
        
        Button returnToBankOptions = new Button("Back");
        returnToBankOptions.setMinSize(100, 50);
        returnToBankOptions.setTranslateX(15);
        returnToBankOptions.setTranslateY(335);
        returnToBankOptions.setOnMouseClicked((MouseEvent t) -> {
            startBank(depositOptions);
        });
        
        depositOptions.getChildren().addAll(amountDep, currentCredits,
                bankCredits, depositLabel, depositButton, returnToBankOptions);
        
        withdrawOptions = new Group();
        
        withdrawButton.setMinSize(200, 50);
        withdrawButton.setTranslateX(200);
        withdrawButton.setTranslateY(250);
        withdrawButton.setOnMouseClicked((MouseEvent t) -> {
            myBank.withdraw(Integer.parseInt(amountWith.getText()));
            player.withdraw(Integer.parseInt(amountWith.getText()));
            currentCredits2.setText("Player Credits: " + player.getCredits());
            bankCredits2.setText("Bank Credits: " + myBank.getCredits());
            currentCredits.setText("Player Credits: " + player.getCredits());
            bankCredits.setText("Bank Credits: " + myBank.getCredits());
            amountWith.setText("");
            withdrawButton.setDisable(true);
            amountDep.setText("");
            depositButton.setDisable(true);
        });
        
        amountWith.addEventFilter(KeyEvent.KEY_TYPED, (KeyEvent event) -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume();
            }
        });
        
        amountWith.setOnKeyReleased((KeyEvent e) -> {
            if (amountWith.getText().isEmpty() || (!amountWith.getText().isEmpty() && Integer.parseInt(amountWith.getText()) > myBank.getCredits())) {
                withdrawButton.setDisable(true);
            } else {
                withdrawButton.setDisable(false);
            }
        });
        Label withdrawLabel = new Label("Withdraw: ");
        withdrawLabel.setMinSize(100, 30);
        currentCredits2.setAlignment(Pos.CENTER);
        bankCredits2.setAlignment(Pos.CENTER);
        withdrawLabel.setAlignment(Pos.CENTER);
        currentCredits2.setMinSize(200, 30);
        bankCredits2.setMinSize(200, 30);
        amountWith.setMinSize(100, 30);
        currentCredits2.setTranslateX(200);
        currentCredits2.setTranslateY(210);
        bankCredits2.setTranslateX(200);
        bankCredits2.setTranslateY(70);
        amountWith.setTranslateX(250);
        amountWith.setTranslateY(140);
        amountWith.setStyle("-fx-border-color: #FFFFFF;\n" +
                "-fx-background-color:#000000;\n" +
                "-fx-text-fill: #FFFFFF;\n" +
                "-fx-border-radius: 10;\n" +
                "-fx-background-radius: 10;");
        withdrawLabel.setTranslateX(150);
        withdrawLabel.setTranslateY(140);
        
        
        
        Button returnToBankOptions2 = new Button("Back");
        returnToBankOptions2.setMinSize(100, 50);
        returnToBankOptions2.setTranslateX(15);
        returnToBankOptions2.setTranslateY(335);
        returnToBankOptions2.setOnMouseClicked((MouseEvent t) -> {
            startBank(withdrawOptions);
        });
        
        withdrawOptions.getChildren().addAll(amountWith, currentCredits2,
                bankCredits2, withdrawLabel, withdrawButton, returnToBankOptions2);
        
        depositOptions.setOpacity(0.0);
        depositOptions.setMouseTransparent(true);
        withdrawOptions.setOpacity(0.0);
        withdrawOptions.setMouseTransparent(true);
        
        anchor.getChildren().addAll(depositOptions, withdrawOptions);
        
        
        
        //*****************Applying for loan*********************//
        
        loanOptions = new Group();
        
        final TextField loanAmount = new TextField();
        Button apply = new Button("Apply");
        Label loanLabel = new Label("Loan Amount: ");
        Label dailyCost = new Label("Daily Cost: 0 Credits");
        Label errorMessage = new Label();
        Button accept = new Button("Accept Loan");
        Label playerCredits = new Label("Current Credits: " + player.getCredits());
        Button returnToMenu = new Button("Back");
        
        dailyCost.setVisible(false);
        accept.setVisible(false);
        errorMessage.setVisible(false);
        playerCredits.setVisible(false);
        
        loanAmount.setMinSize(100, 30);
        loanLabel.setMinSize(100, 30);
        loanAmount.setTranslateX(275);
        loanAmount.setTranslateY(80);
        loanLabel.setTranslateX(175);
        loanLabel.setTranslateY(80);
        
        apply.setMinSize(150, 50);
        apply.setTranslateX(225);
        apply.setTranslateY(150);
        
        errorMessage.setMinSize(400, 100);
        errorMessage.setMaxSize(400, 100);
        errorMessage.setStyle("-fx-text-fill: #FF0000;");
        errorMessage.setWrapText(true);
        errorMessage.setTranslateX(100);
        errorMessage.setTranslateY(220);
        errorMessage.setAlignment(Pos.CENTER);
        
        dailyCost.setMinSize(200, 50);
        dailyCost.setAlignment(Pos.CENTER);
        dailyCost.setTranslateX(200);
        dailyCost.setTranslateY(300);
        
        accept.setMinSize(200, 50);
        accept.setTranslateX(200);
        accept.setTranslateY(250);
        
        playerCredits.setMinSize(400, 50);
        playerCredits.setAlignment(Pos.CENTER);
        playerCredits.setTranslateX(100);
        playerCredits.setTranslateY(350);
        
        returnToMenu.setMinSize(100, 50);
        returnToMenu.setTranslateX(15);
        returnToMenu.setTranslateY(335);
        returnToMenu.setOnMouseClicked((MouseEvent t) -> {
            startMenu(loanOptions);
        });
        
        loanAmount.addEventFilter(KeyEvent.KEY_TYPED, (KeyEvent event) -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume();
            }
        });
        
        loanAmount.setOnKeyReleased((KeyEvent e) -> {
            if (loanAmount.getText().isEmpty()) {
                apply.setDisable(true);
            } else {
                apply.setDisable(false);
            }
        });
        
        apply.setOnMouseClicked((MouseEvent t) -> {
            String message = myBank.getResponse(Integer.parseInt(loanAmount.getText()), player.getTotalCredits(), player.getBounty());
            if (!message.isEmpty()) {
                dailyCost.setVisible(false);
                accept.setVisible(false);
                playerCredits.setVisible(false);
                errorMessage.setText(message);
                errorMessage.setVisible(true);
            } else {
                errorMessage.setVisible(false);
                int cost = (int)((0.1 - player.getInvestorSkillPoints() * .0025) * Integer.parseInt(loanAmount.getText()));
                dailyCost.setText("Daily Cost: " + cost + " Credits");
                playerCredits.setText("Current Credits: " + player.getCredits());
                dailyCost.setVisible(true);
                accept.setVisible(true);
                playerCredits.setVisible(true);
                
                accept.setOnMouseClicked((MouseEvent e) -> {
                    myBank.addLoan(Integer.parseInt(loanAmount.getText()), cost);
                    player.withdraw(Integer.parseInt(loanAmount.getText()));
                    playerCredits.setText("Current Credits: " + player.getCredits());
                    accept.setDisable(true);
                });
            }
        });
        
        loanOptions.getChildren().addAll(loanAmount, apply, accept, dailyCost, 
                loanLabel, errorMessage, playerCredits, returnToMenu);
        loanOptions.setOpacity(0);
        loanOptions.setMouseTransparent(true);
        anchor.getChildren().add(loanOptions);
        
        //******************STOCK MARKET*******************//
        
        StockMarket market = GameController.getGameData().getStockMarket();
        
        stockMarketOptions = new Group();
        
        Button returnToMenuFromSM = new Button("Back");
        returnToMenuFromSM.setMinSize(100, 50);
        returnToMenuFromSM.setTranslateX(15);
        returnToMenuFromSM.setTranslateY(335);
        returnToMenuFromSM.setOnMouseClicked((MouseEvent t) -> {
            startMenu(stockMarketOptions);
        });
        
        ListView<String> stockList = new ListView<>();
        ObservableList<String> corps = FXCollections.observableArrayList();
        
        for (int i = 0; i < StockMarket.NUM_CORPS; i++) {
            corps.add(market.getCorporation(i).name);
        }
        
        stockList.setItems(corps);
        
        stockList.setPrefSize(200, 260);
        stockList.setTranslateX(40);
        stockList.setTranslateY(40);
        
        
        
        Button buyStock = new Button("Buy share");
        
        buyStock.setTranslateX(330);
        buyStock.setTranslateY(300);
        buyStock.setDisable(true);
        
        Button sellStock = new Button("Sell share");
        
        sellStock.setTranslateX(420);
        sellStock.setTranslateY(300);
        sellStock.setDisable(true);
        
        buyStock.setOnMouseClicked((MouseEvent e) -> {
            StockCorporation corp = market.getCorporation(stockList.getSelectionModel().getSelectedIndex());
            player.buyStocks(corp, 1);
            
            //temporary
            System.out.println("Current credits: " + player.getCredits());
            
            //update buttons
            buyStock.setDisable(player.getCredits() < corp.currentValue());
            sellStock.setDisable(!player.hasStock(corp));
        });
        
        sellStock.setOnMouseClicked((MouseEvent e) -> {
            StockCorporation corp = market.getCorporation(stockList.getSelectionModel().getSelectedIndex());
            player.sellStocks(corp, 1);
            
            //temporary
            System.out.println("Current credits: " + player.getCredits());
            
            //update buttons
            buyStock.setDisable(player.getCredits() < corp.currentValue());
            sellStock.setDisable(!player.hasStock(corp));
        });
        
        
        
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        
        LineChart stockChart = new LineChart(xAxis, yAxis);
        
        stockChart.setTitle("Stock Values");
        stockChart.setPrefSize(320, 240);
        stockChart.setTranslateX(260);
        stockChart.setTranslateY(40);
        
        stockList.getSelectionModel().selectedIndexProperty().addListener((observable, oldVal, newVal) -> {
            StockCorporation corp = market.getCorporation(newVal.intValue());
            
            //update buttons
            buyStock.setDisable(player.getCredits() < corp.currentValue());
            sellStock.setDisable(!player.hasStock(corp));
            
            //update stock chart
            stockChart.setTitle("Stock Values: " + corp.name);
            stockChart.getData().clear();
            
            LineChart.Series series = new XYChart.Series();
            LinkedList<Double> vals = corp.getValues();
            
            for (int i = 0; i < vals.size(); i++) {
                System.out.println("Should add pair" + i + " and " + vals.get(i));
                series.getData().add(new LineChart.Data(i, vals.get(i)));
            }
            
            stockChart.getData().add(series);
        });
        
        stockMarketOptions.getChildren().addAll(returnToMenuFromSM, stockList,
                buyStock, sellStock, stockChart);
        stockMarketOptions.setOpacity(0);
        stockMarketOptions.setMouseTransparent(true);
        anchor.getChildren().add(stockMarketOptions);
    }
    
    private void startBank(Group oldGroup) {
        fadeSwitch(oldGroup, bankOptions);
    }
    
    private void startMenu(Group oldGroup) {
        fadeSwitch(oldGroup, menu);
    }
    
    private void startLoan(Group oldGroup) {
        fadeSwitch(oldGroup, loanOptions);
    }
    
    private void startStockMarket(Group oldGroup) {
        fadeSwitch(oldGroup, stockMarketOptions);
    }
    
    private void updateStockChart() {
        
    }
    
    private void updateStockButtons() {
        
    }
    
    private void fadeSwitch(Group oldGroup, Group newGroup) {
        FadeTransition fadeOut = new FadeTransition(Duration.millis(500), oldGroup);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);
        oldGroup.setMouseTransparent(true);
        fadeOut.setOnFinished((ActionEvent e) -> {
            FadeTransition fadeIn = new FadeTransition(Duration.millis(500), newGroup);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            newGroup.setMouseTransparent(false);
            fadeIn.play();
        });
        fadeOut.play();
    }

    private void startDeposit() {
        fadeSwitch(bankOptions, depositOptions);
    }

    private void startWithdraw() {
        fadeSwitch(bankOptions, withdrawOptions);
    }
    
}
