package com.example.lab5app;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import java.io.*;

//Ремонт мобильных телефонов
public class Main extends Application {
    @Override
    public void start(Stage stage)  {
        HBox request = new HBox();
        Label rq = new Label("Заявка на ремонт");
        request.setAlignment(Pos.TOP_CENTER);
        request.getChildren().add(rq);
        HBox.setMargin(rq, new Insets(20.0, 0, 0, 0));
        HBox reg1 = new HBox();
        ObservableList<String> phones = FXCollections.observableArrayList("Apple", "Samsung", "Xiaomi", "Honor", "Huawei", "Oppo", "Vivo", "Realme", "Nokia", "ZTE");
        ComboBox<String> phonesCombobox = new ComboBox<>(phones);
        phonesCombobox.promptTextProperty().set("Выберите производителя");
        TextField name = new TextField();
        name.promptTextProperty().set("Имя");
        TextField numb = new TextField();
        numb.promptTextProperty().set("Номер телефона");
        HBox regnumname = new HBox(name,numb);
        regnumname.setSpacing(3);
        reg1.getChildren().addAll(phonesCombobox, regnumname);
        reg1.setAlignment(Pos.CENTER);
        reg1.setSpacing(150);
        HBox.setMargin(phonesCombobox, new Insets(50.0, 0, 0, 0));
        HBox.setMargin(name, new Insets(50.0, 0, 0, 0));
        HBox.setMargin(numb, new Insets(50.0, 0, 0, 0));
        HBox numGar = new HBox();
        Label isgar = new Label("Имеется ли у вас гарантия?");
        RadioButton yes = new RadioButton("Да");
        RadioButton no = new RadioButton("Нет");
        yes.setOnAction(e-> {
                yes.setSelected(true);
                no.setSelected(false);
        });
        no.setOnAction(e->{
                no.setSelected(true);
                yes.setSelected(false);
        });
        HBox radio = new HBox(yes, no);
        radio.setSpacing(10);
        numGar.setAlignment(Pos.CENTER);
        numGar.setSpacing(390);
        numGar.getChildren().addAll(isgar, radio);
        HBox.setMargin(isgar, new Insets(30.0, 0, 0, 0));
        HBox.setMargin(radio, new Insets(30.0, 0, 0, 0));
        VBox privacy = new VBox();
        CheckBox stat1 = new CheckBox("Я согласен с условиями ремонта");
        CheckBox stat2 = new CheckBox("Я согласен с Политикой конфиденциальности");
        privacy.getChildren().addAll(stat1, stat2);
        stat1.setPadding(new Insets(0,0,0,30.0));
        stat2.setPadding(new Insets(0,0,0,30.0));
        VBox.setMargin(privacy, new Insets(30.0,0,0,0));
        privacy.setSpacing(3);
        HBox desofprob = new HBox();
        Label ds = new Label("Опишите, пожалуйста, возникшие проблемы");
        desofprob.setAlignment(Pos.CENTER);
        desofprob.getChildren().add(ds);
        HBox.setMargin(ds, new Insets(30.0, 0, 0,0));
        HBox textA = new HBox();
        TextArea txt = new TextArea();
        txt.setPrefColumnCount(40);
        txt.setPrefRowCount(5);
        HBox.setMargin(txt, new Insets(15.0,0,0,0));
        textA.setAlignment(Pos.CENTER);
        textA.getChildren().add(txt);
        HBox btsect = new HBox();
        HBox results = new HBox();
        Button bt = new Button("Оставить заявку");
        Label res = new Label();
        results.getChildren().add(res);
        results.setAlignment(Pos.CENTER);
        HBox.setMargin(res, new Insets(10,0,5,0));
        bt.setOnAction(e ->{
            if (stat1.isSelected() && stat2.isSelected()) {
                res.setText("Заявка принята");
                String textproizv = phonesCombobox.getValue();
                String name1 = name.getText();
                String num1 = numb.getText();
                String isgar1;
                if (yes.isSelected()) isgar1 = "Has";
                else if (no.isSelected()) isgar1 = "Hasn't";
                else isgar1 = "No information";
                String txt1 = txt.getText();
                try {
                    String data = "\nProduced by: " + textproizv + "\nName of client: " + name1 + "\nMobile number of client: " + num1 + "\nDoes a client have a guarantee: " + isgar1 + "\nDescription of a problem: " + txt1 + "\n\n";
                    FileActions.openFile();
                    FileActions.file.seek(FileActions.file.length());
                    FileActions.file.writeBytes(data);
                    FileActions.file.close();
                } catch (IOException exception) {
                    throw new RuntimeException(exception);
                }
            }
            if (!stat1.isSelected() && !stat2.isSelected()) res.setText("Для оставления заявки вы должны согласиться с условиями ремонта и Политикой конфиденциальности");
            else if (!stat1.isSelected()) res.setText("Вы не приняли условия ремонта!");
            else if (!stat2.isSelected()) res.setText("Вы не согласились с нащей Политикой конфиденциальности");
        });
        String buff = "Выберите производителя";
        Button clr = new Button("Очистить заявку");
        clr.setOnAction(e->{
                phonesCombobox.setValue(buff);
                name.clear();
                numb.clear();
                txt.clear();
                stat1.setSelected(false);
                stat2.setSelected(false);
                yes.setSelected(false);
                no.setSelected(false);
                res.setText("Заявка очищена");

        });
        btsect.getChildren().addAll(bt, clr);
        btsect.setSpacing(5);
        HBox.setMargin(bt, new Insets(30,0,0,0));
        HBox.setMargin(clr, new Insets(30,0,0,0));
        btsect.setAlignment(Pos.CENTER);
        VBox root = new VBox(request, reg1, numGar,privacy, desofprob, textA, btsect, results);
        Scene scene = new Scene(root, 700,500);
        stage.setScene(scene);
        stage.setTitle("Ремонт мобильных телефонов");
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}