module com.kurs.obycheniekursprilogenie {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires java.desktop;


    opens com.kurs.obycheniekursprilogenie to javafx.fxml;
    exports com.kurs.obycheniekursprilogenie;
    exports com.kurs.obycheniekursprilogenie.Controllers;
    opens com.kurs.obycheniekursprilogenie.Controllers to javafx.fxml;
}