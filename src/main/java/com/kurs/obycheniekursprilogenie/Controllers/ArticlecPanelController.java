package com.kurs.obycheniekursprilogenie.Controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;
import com.kurs.obycheniekursprilogenie.DB;
import com.kurs.obycheniekursprilogenie.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class ArticlecPanelController
{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button add_article_btn;

    @FXML
    private VBox panelVBox;

    @FXML
    private TextField full_art;

    @FXML
    private TextField small_art;

    @FXML
    private Label checklabel;

    private String link;
    private boolean check;

    @FXML
    void initialize() throws SQLException, IOException
    {
        DB db = new DB();

        updateVbox();

        add_article_btn.setOnAction(event ->
        {
            String fullart = full_art.getCharacters().toString();
            String smallart = small_art.getCharacters().toString();
            check = db.checkArticle(smallart);
            if (!check)
            {
                db.addArticle(fullart, smallart);
                full_art.setText("");
                small_art.setText("");
                checklabel.setText("Добавлено");
                try {
                    updateVbox();
                } catch (SQLException | IOException e) {e.printStackTrace();}
            }else
            {
                checklabel.setText("Введите другое сокращение");
            }
        });

    }


    public void updateVbox() throws SQLException, IOException
    {
        panelVBox.getChildren().clear();

        DB db = new DB();

        ResultSet resultSet = db.getArticles();
        while (resultSet.next())
        {
            Node node = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("article-kurs.fxml")));

            Label intro = (Label) node.lookup("#intro");
            intro.setText(resultSet.getString("smallart"));
            intro.setId(resultSet.getString("id"));

            intro.setAccessibleHelp(resultSet.getString("fullart")); // я понимаю что это странное решение)))
            // но я вспомнил как я марочался с вызовом resultSet после того как sql запрос уже выполнен и мне кажется это хорошая хитрость
            // я думал над созданием HashMap(так возможно правильнее было бы), но так проще и короче чуть-чуть
            // в Androide я много провожусь c различными списками

            node.setOnMouseEntered(mouseEvent -> {node.setStyle("-fx-background-color: #707173");});
            node.setOnMouseExited(mouseEvent -> {node.setStyle("-fx-background-color: #343434");});

            node.setOnMouseClicked(event ->
            {
                try
                {
                    link = intro.getAccessibleHelp();
                    java.awt.Desktop.getDesktop().browse(URI.create(link));
                } catch (IOException e) {e.printStackTrace();}
            });

            panelVBox.getChildren().add(node);
            panelVBox.setSpacing(10);
        }
    }

}