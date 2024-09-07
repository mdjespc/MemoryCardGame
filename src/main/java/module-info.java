module memorycardgame.memorycardgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens memorycardgame.memorycardgame to javafx.fxml;
    exports memorycardgame.memorycardgame;
}