module com.lucasfagunda.semestral {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.lucasfagunda.semestral to javafx.fxml;
    exports com.lucasfagunda.semestral;
}
