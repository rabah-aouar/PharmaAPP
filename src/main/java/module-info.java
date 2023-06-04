module PharmaAPP {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;
    requires javafx.swing;
    //requires org.hibernate.orm.core;
    //requires org.hibernate.commons.annotations;
    requires java.xml;
    requires java.sql;
    //requires org.xerial.sqlitejdbc;

    requires MaterialFX;
    requires de.jensd.fx.glyphs.fontawesome;
    //requires net.bytebuddy;

    requires org.controlsfx.controls;
    requires opencv;



    opens com.pharmaApp.pharmaAppFX to javafx.fxml, javafx.base, javafx.controls, org.controlsfx.controls;
    opens com.pharmaApp.pharmaAppFX.controllers to javafx.fxml, javafx.base, javafx.controls, org.controlsfx.controls;



    exports com.pharmaApp.pharmaAppFX;
    exports com.pharmaApp.pharmaAppFX.controllers;

}