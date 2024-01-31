module JavaFXtoMaven {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires java.desktop;
	requires kernel;
	requires layout;
	requires io;
	requires styled.xml.parser;
	
	opens application to javafx.graphics, javafx.fxml;
}
