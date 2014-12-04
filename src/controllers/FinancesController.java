package controllers;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
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
    private StockMarket myStockMarket;
    private StockCorporation selected;
    
    private Group menu;
    private Group bankOptions;
    private Group depositOptions;
    private Group withdrawOptions;
    private Group loanOptions;
    private Group stockMarketOptions;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Player p = GameController.getGameData().getPlayer();
        myBank = GameController.getGameData().getBank();
        myStockMarket = GameController.getGameData().getStockMarket();
        
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
        
        Label currentCredits = new Label("Player Credits: " + p.getCredits());
        Label bankCredits = new Label("Bank Credits: " + myBank.getCredits());
        Label currentCredits2 = new Label("Player Credits: " + p.getCredits());
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
            p.spend(Integer.parseInt(amountDep.getText()));
            currentCredits.setText("Player Credits: " + p.getCredits());
            bankCredits.setText("Bank Credits: " + myBank.getCredits());
            currentCredits2.setText("Player Credits: " + p.getCredits());
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
            if (amountDep.getText().isEmpty() || (!amountDep.getText().isEmpty() && Integer.parseInt(amountDep.getText()) > p.getCredits())) {
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
            p.withdraw(Integer.parseInt(amountWith.getText()));
            currentCredits2.setText("Player Credits: " + p.getCredits());
            bankCredits2.setText("Bank Credits: " + myBank.getCredits());
            currentCredits.setText("Player Credits: " + p.getCredits());
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
        Label playerCredits = new Label("Current Credits: " + p.getCredits());
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
            String message = myBank.getResponse(Integer.parseInt(loanAmount.getText()), p.getTotalCredits(), p.getBounty());
            if (!message.isEmpty()) {
                dailyCost.setVisible(false);
                accept.setVisible(false);
                playerCredits.setVisible(false);
                errorMessage.setText(message);
                errorMessage.setVisible(true);
            } else {
                errorMessage.setVisible(false);
                int cost = (int)((0.1 - p.getInvestorSkillPoints() * .0025) * Integer.parseInt(loanAmount.getText()));
                dailyCost.setText("Daily Cost: " + cost + " Credits");
                playerCredits.setText("Current Credits: " + p.getCredits());
                dailyCost.setVisible(true);
                accept.setVisible(true);
                playerCredits.setVisible(true);
                
                accept.setOnMouseClicked((MouseEvent e) -> {
                    myBank.addLoan(Integer.parseInt(loanAmount.getText()), cost);
                    p.withdraw(Integer.parseInt(loanAmount.getText()));
                    playerCredits.setText("Current Credits: " + p.getCredits());
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
        
        stockMarketOptions = new Group();
        
        Button returnToMenuFromSM = new Button("Back");
        returnToMenuFromSM.setMinSize(50, 30);
        returnToMenuFromSM.setTranslateX(15);
        returnToMenuFromSM.setTranslateY(370);
        returnToMenuFromSM.setOnMouseClicked((MouseEvent t) -> {
            startMenu(stockMarketOptions);
        });
        
        GridPane grid = new GridPane();
        grid.setId("stock_grid");
        Label nameHeader = new Label("Name");
        nameHeader.setMinSize(75, 30);
        nameHeader.setAlignment(Pos.CENTER);
        nameHeader.setFont(Font.font("System Bold"));
        nameHeader.setBorder(new Border(new BorderStroke(Color.WHITE, 
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
        Label cvHeader = new Label("Current");
        cvHeader.setMinSize(80, 30);
        cvHeader.setAlignment(Pos.CENTER);
        cvHeader.setFont(Font.font("System Bold"));
        cvHeader.setBorder(new Border(new BorderStroke(Color.WHITE, 
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
        Label pvHeader = new Label("Previous");
        pvHeader.setMinSize(80, 30);
        pvHeader.setAlignment(Pos.CENTER);
        pvHeader.setFont(Font.font("System Bold"));
        pvHeader.setBorder(new Border(new BorderStroke(Color.WHITE, 
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
        Label pcHeader = new Label("% Change");
        pcHeader.setMinSize(80, 30);
        pcHeader.setAlignment(Pos.CENTER);
        pcHeader.setFont(Font.font("System Bold"));
        pcHeader.setBorder(new Border(new BorderStroke(Color.WHITE, 
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
        Label chartHeader = new Label("View Chart");
        chartHeader.setMinSize(100, 30);
        chartHeader.setAlignment(Pos.CENTER);
        chartHeader.setFont(Font.font("System Bold"));
        chartHeader.setBorder(new Border(new BorderStroke(Color.WHITE, 
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            
        Label buySellHeader = new Label("Buy/Sell");
        buySellHeader.setMinSize(100, 30);
        buySellHeader.setAlignment(Pos.CENTER);
        buySellHeader.setFont(Font.font("System Bold"));
        buySellHeader.setBorder(new Border(new BorderStroke(Color.WHITE, 
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        
        grid.add(nameHeader, 0, 0);
        grid.add(cvHeader, 1, 0);
        grid.add(pvHeader, 2, 0);
        grid.add(pcHeader, 3, 0);
        grid.add(chartHeader, 4, 0);
        grid.add(buySellHeader, 5, 0);
        
        StockCorporation[] corps = myStockMarket.getCorporations();
        for (int i = 0; i < corps.length; i++) {
            Label name = new Label(corps[i].getName());
            name.setMinSize(75, 30);
            name.setAlignment(Pos.CENTER);
            name.setBorder(new Border(new BorderStroke(Color.WHITE, 
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            
            Label currentValue = new Label(String.format("%.3f", corps[i].currentValue()));
            currentValue.setMinSize(80, 30);
            currentValue.setAlignment(Pos.CENTER);
            currentValue.setBorder(new Border(new BorderStroke(Color.WHITE, 
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            
            Label previousValue = new Label(String.format("%.3f", corps[i].previousValue()));
            previousValue.setMinSize(80, 30);
            previousValue.setAlignment(Pos.CENTER);
            previousValue.setBorder(new Border(new BorderStroke(Color.WHITE, 
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            
            Label percentChange = new Label(String.format("%.3f", corps[i].percentChange()));
            percentChange.setMinSize(80, 30);
            percentChange.setAlignment(Pos.CENTER);
            percentChange.setBorder(new Border(new BorderStroke(Color.WHITE, 
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            
            Button chartView = new Button("View");
            chartView.setMinSize(98, 28);
            chartView.setTranslateX(1);
            chartView.setTranslateY(0);
            chartView.setBorder(new Border(new BorderStroke(Color.WHITE, 
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            
            
            ArrayList<XYChart.Data<Integer, Double>> data = new ArrayList<>();
            for (int j = 0; j < corps[i].getData().size(); j++) {
                data.add(new XYChart.Data<>(j, corps[i].getData().get(corps[i].getData().size() - (j + 1))));
            }
            
            XYChart.Series<Integer, Double> series = new XYChart.Series<>(FXCollections.observableList(data));
            ArrayList<XYChart.Series<Integer, Double>> dataList = new ArrayList<>();
            dataList.add(series);
            LineChart plot = new LineChart(new NumberAxis("Turns", 0, corps[i].getData().size(), corps[i].getData().size() / 5 + 1), new NumberAxis("Price", 0, 1000, 50), FXCollections.observableList(dataList));
            plot.setMinSize(600, 400);
            plot.setCreateSymbols(false);
            plot.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            plot.setVisible(false);
            
            Button hideChart = new Button ("Hide");
            hideChart.setOpacity(0.8);
            hideChart.setMouseTransparent(true);
            hideChart.setVisible(false);
            hideChart.setTranslateX(30);
            hideChart.setTranslateY(340);
            
            hideChart.setOnMouseClicked((MouseEvent t) -> {
                hideChart.setVisible(false);
                plot.setTranslateY(0);
                TranslateTransition translate = new TranslateTransition(Duration.millis(750), plot);
                translate.setFromY(0);
                translate.setToY(600);
                translate.setOnFinished((ActionEvent e) -> {
                    plot.setVisible(false);
                    hideChart.setMouseTransparent(true);
                });
                translate.play();
            });
            stockMarketOptions.getChildren().addAll(plot, hideChart);
            
            chartView.setOnMouseClicked((MouseEvent t) -> {
                plot.setVisible(true);
                plot.toFront();
                plot.setTranslateY(600);
                TranslateTransition translate = new TranslateTransition(Duration.millis(750), plot);
                translate.setFromY(600);
                translate.setToY(0);
                translate.setOnFinished((ActionEvent e) -> {
                    hideChart.setVisible(true);
                    hideChart.setMouseTransparent(false);
                    hideChart.toFront();
                });
                translate.play();
            });
            
            Button buySell = new Button("Buy/Sell");
            buySell.setMinSize(98, 28);
            buySell.setTranslateX(1);
            buySell.setTranslateY(0);
            buySell.setBorder(new Border(new BorderStroke(Color.WHITE, 
                    BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            
            AnchorPane buySellAnchor = new AnchorPane();
            buySellAnchor.setMinSize(600, 400);
            buySellAnchor.setBackground(new Background(new BackgroundFill(Color.rgb(50, 50, 50, 0.9), CornerRadii.EMPTY, Insets.EMPTY)));
            
            Label buyCorpStock = new Label("Buy " + corps[i].getName() + " Stock:");
            Label sellCorpStock = new Label("Sell " + corps[i].getName() + " Stock:");
            Label ownedStock = new Label(corps[i].getName() + " Stock Owned: " + p.getStockPortfolio().getShares(corps[i]) + " Shares");
            
            int maxQuanBuy = (int)(p.getCredits() / corps[i].currentValue());
            int maxQuanSell = p.getStockPortfolio().getShares(corps[i]);
            
            Slider buyStockAmount = new Slider(0, maxQuanBuy, 0.0);
            buyStockAmount.setBlockIncrement(1);
            buyStockAmount.setMajorTickUnit(1);
            buyStockAmount.setMinorTickCount(0);
            buyStockAmount.setSnapToTicks(true);
            
            Slider sellStockAmount = new Slider(0, maxQuanSell, 0.0);
            sellStockAmount.setBlockIncrement(1);
            sellStockAmount.setMajorTickUnit(1);
            sellStockAmount.setMinorTickCount(0);
            sellStockAmount.setSnapToTicks(true);
            
            TextField buyStockQuan = new TextField();
            TextField sellStockQuan = new TextField();
            
            Label buyCost = new Label();
            Label sellGain = new Label();
            
            Button buyStock = new Button("Buy");
            buyStock.setDisable(true);
            Button sellStock = new Button("Sell");
            sellStock.setDisable(true);
            
            final int k = i;
            
            buyStockAmount.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                if (!buyStockQuan.getText().isEmpty() && Integer.parseInt(buyStockQuan.getText()) != new_val.intValue()) {
                    buyStockQuan.setText(new_val.intValue() + "");
                } else {
                    buyStockQuan.setText("0");
                }
                if (buyStockAmount.getValue() == 0 || (buyStock.isDisable() && buyStockAmount.getMax() == buyStockAmount.getValue())) {
                    buyStock.setDisable(true);
                } else {
                    buyStock.setDisable(false);
                }
                
                buyCost.setText((int)(buyStockAmount.getValue() * corps[k].currentValue()) + " Credits");
            });
            
            sellStockAmount.valueProperty().addListener((ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                 if (!sellStockQuan.getText().isEmpty() && Integer.parseInt(sellStockQuan.getText()) != new_val.intValue()) {
                     sellStockQuan.setText(new_val.intValue() + "");
                 } else {
                    sellStockQuan.setText("0");
                }
                if (sellStockAmount.getValue() == 0 || (sellStock.isDisable() && sellStockAmount.getMax() == sellStockAmount.getValue())) {
                    sellStock.setDisable(true);
                } else {
                    sellStock.setDisable(false);
                }
                sellGain.setText((int)(sellStockAmount.getValue() * corps[k].currentValue()) + " Credits");
            });
            
            buyStockQuan.addEventFilter(KeyEvent.KEY_TYPED, (KeyEvent event) -> {
                if (!event.getCharacter().matches("[0-9]")) {
                    event.consume();
                }
            });
        
            buyStockQuan.setOnKeyReleased((KeyEvent e) -> {
                if (buyStockQuan.getText().isEmpty()) {
                    buyStock.setDisable(true);
                    buyStockAmount.setValue(0);
                }  else if (!buyStockQuan.getText().isEmpty() && Integer.parseInt(buyStockQuan.getText()) > buyStockAmount.getMax()) {
                    buyStock.setDisable(true);
                    buyStockAmount.setValue(buyStockAmount.getMax());
                } else {
                    buyStock.setDisable(false);
                    buyStockAmount.setValue(Integer.parseInt(buyStockQuan.getText()));
                }
                

            });
            
            sellStockQuan.addEventFilter(KeyEvent.KEY_TYPED, (KeyEvent event) -> {
                if (!event.getCharacter().matches("[0-9]")) {
                    event.consume();
                }
            });
        
            sellStockQuan.setOnKeyReleased((KeyEvent e) -> {
                 if (sellStockQuan.getText().isEmpty()) {
                    sellStock.setDisable(true);
                    sellStockAmount.setValue(0);
                }  else if (!sellStockQuan.getText().isEmpty() && Integer.parseInt(sellStockQuan.getText()) > sellStockAmount.getMax()) {
                    sellStock.setDisable(true);
                    sellStockAmount.setValue(sellStockAmount.getMax());
                } else {
                    sellStock.setDisable(false);
                    sellStockAmount.setValue(Integer.parseInt(sellStockQuan.getText()));
                }
            });
            
            Label credits = new Label("Player Credits: " + p.getCredits());
            credits.setMinSize(200, 30);
            credits.setTranslateX(400);
            credits.setTranslateY(350);
            
            
            buyStock.setOnMouseClicked((MouseEvent t) -> {
                int amount = (int)(buyStockAmount.getValue());
                p.getStockPortfolio().buyStock(corps[k], amount);
                p.spend((int)(corps[k].currentValue() * amount));
                buyStockAmount.setMax((int)(p.getCredits() / corps[k].currentValue()));
                sellStockAmount.setMax(p.getStockPortfolio().getShares(corps[k]));
                buyStockAmount.setValue(0);
                sellStockAmount.setValue(0);
                ownedStock.setText(corps[k].getName() + " Stock Owned: " + p.getStockPortfolio().getShares(corps[k]) + " Shares");
                credits.setText("Player Credits: " + p.getCredits());
            });
            
            sellStock.setOnMouseClicked((MouseEvent t) -> {
                int amount = (int)(sellStockAmount.getValue());
                p.getStockPortfolio().sellStock(corps[k], amount);
                p.earn((int)(corps[k].currentValue() * amount));
                buyStockAmount.setMax((int)(p.getCredits() / corps[k].currentValue()));
                sellStockAmount.setMax(p.getStockPortfolio().getShares(corps[k]));
                buyStockAmount.setValue(0);
                sellStockAmount.setValue(0);
                ownedStock.setText(corps[k].getName() + " Stock Owned: " + p.getStockPortfolio().getShares(corps[k]) + " Shares");
                credits.setText("Player Credits: " + p.getCredits());
            });
            
            
            buyCorpStock.setMinSize(600, 30);
            buyCorpStock.setTranslateY(30);
            buyCorpStock.setAlignment(Pos.CENTER);
            
            buyStockAmount.setMinSize(100, 30);
            buyStockAmount.setTranslateX(175);
            buyStockAmount.setTranslateY(70);
            
            buyStockQuan.setMinSize(100, 30);
            buyStockQuan.setTranslateX(325);
            buyStockQuan.setTranslateY(70);
            
            buyStock.setMinSize(150, 40);
            buyStock.setTranslateX(175);
            buyStock.setTranslateY(115);
            
            buyCost.setMinSize(150, 40);
            buyCost.setAlignment(Pos.CENTER_LEFT);
            buyCost.setTranslateX(330);
            buyCost.setTranslateY(115);
            
            ownedStock.setMinSize(600, 40);
            ownedStock.setAlignment(Pos.CENTER);
            ownedStock.setTranslateY(170);
            
            sellCorpStock.setMinSize(600, 30);
            sellCorpStock.setTranslateY(230);
            sellCorpStock.setAlignment(Pos.CENTER);
            
            sellStockAmount.setMinSize(100, 30);
            sellStockAmount.setTranslateX(175);
            sellStockAmount.setTranslateY(270);
            
            sellStockQuan.setMinSize(100, 30);
            sellStockQuan.setTranslateX(325);
            sellStockQuan.setTranslateY(270);
            
            sellStock.setMinSize(150, 40);
            sellStock.setTranslateX(175);
            sellStock.setTranslateY(320);
            
            sellGain.setMinSize(150, 40);
            sellGain.setAlignment(Pos.CENTER_LEFT);
            sellGain.setTranslateX(330);
            sellGain.setTranslateY(320);
            
            Button hideButton = new Button("Hide");
            hideButton.setTranslateX(10);
            hideButton.setTranslateY(360);
            
            buySellAnchor.setTranslateY(600);
            buySellAnchor.setVisible(false);
            
            hideButton.setOnMouseClicked((MouseEvent e) -> {
                TranslateTransition translate = new TranslateTransition(Duration.millis(750), buySellAnchor);
                translate.setFromY(0);
                translate.setToY(600);
                translate.setOnFinished((ActionEvent t) -> {
                   buySellAnchor.setVisible(false);
                });
                translate.play();
            });
            
            buySellAnchor.getChildren().addAll(buyCorpStock, buyStockAmount,
                    buyStockQuan, buyStock, ownedStock, sellCorpStock,
                    sellStockAmount, sellStockQuan, sellStock, hideButton,
                    credits, sellGain, buyCost);
            
            buySell.setOnMouseClicked((MouseEvent t) -> {
                buySellAnchor.setTranslateY(600);
                buySellAnchor.setVisible(true);
                buySellAnchor.toFront();
                TranslateTransition translate = new TranslateTransition(Duration.millis(750), buySellAnchor);
                translate.setFromY(600);
                translate.setToY(0);
                translate.play();
            });
            
            stockMarketOptions.getChildren().add(buySellAnchor);
            
            grid.add(name, 0, i + 1);
            grid.add(currentValue, 1, i + 1);
            grid.add(previousValue, 2, i + 1);
            grid.add(percentChange, 3, i + 1);
            grid.add(chartView, 4, i + 1);
            grid.add(buySell, 5, i + 1);
            
            
        }
        
        grid.setStyle("-fx-border-color: #FFFFFF;\n" 
                + "-fx-opacity: 1;");
        grid.setVgap(0.0);
        grid.setTranslateX(40);
        grid.setTranslateY(10);
        
        stockMarketOptions.getChildren().addAll(grid, returnToMenuFromSM);
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
