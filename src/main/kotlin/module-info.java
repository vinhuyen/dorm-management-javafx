module app.ktx {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires java.sql;
    requires mysql.connector.java;


    opens app.ktx to javafx.fxml;
    opens app.ktx.routes to javafx.fxml;
    opens app.ktx.controllers to javafx.fxml;
    opens app.ktx.services to javafx.fxml;
    opens app.ktx.repositories to javafx.fxml;
    opens app.ktx.utils to javafx.fxml;
    opens app.ktx.models to javafx.fxml, javafx.base;

    exports app.ktx;
}