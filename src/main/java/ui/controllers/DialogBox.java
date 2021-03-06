package ui.controllers;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

/**
 * An example of a custom control using FXML. This control represents a dialog
 * box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        assert text != null : "DialogBox text is null";
        assert img != null : "DialogBox img is null";

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create circle mask to clip the display picture
        double centerX = displayPicture.getFitWidth() / 2.0;
        double centerY = displayPicture.getFitHeight() / 2.0;
        double radius = Math.min(displayPicture.getFitWidth(), displayPicture.getFitHeight()) / 2.0;
        Circle circleMask = new Circle(centerX, centerY, radius);

        dialog.setText(text);
        displayPicture.setImage(img);
        displayPicture.setClip(circleMask);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the
     * right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Shows a User dialog containing the text and the given img
     *
     * @param text
     * @param image
     * @return
     */
    public static DialogBox getUserDialog(String text, Image image) {
        return new DialogBox(text, image);
    }

    /**
     * Shows a Duke dialog containing the text and the given img
     *
     * @param text
     * @param image
     * @return
     */
    public static DialogBox getDukeDialog(String text, Image image) {
        var db = new DialogBox(text, image);
        db.flip();
        return db;
    }
}
