package com.example.fxfilemanager;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Controller {
    @FXML
    VBox leftPanel, rightPanel;

    public void btnExitAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void btnCopyAction(ActionEvent actionEvent) {
        PanelController leftPC = (PanelController) leftPanel.getProperties().get("ctrl");
        PanelController rightPC = (PanelController) rightPanel.getProperties().get("ctrl");
        if (leftPC.getSelectedFilename() == null && rightPC.getSelectedFilename() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "File not selected", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        PanelController srcPC = null, dstPC = null;
        if (leftPC.getSelectedFilename() != null) {
            srcPC = leftPC;
            dstPC = rightPC;
        }
        if (rightPC.getSelectedFilename() != null) {
            srcPC = rightPC;
            dstPC = leftPC;
        }
        Path srcPath = Paths.get(srcPC.getCurrentPath(), srcPC.getSelectedFilename());
        Path dstPath = Paths.get(dstPC.getCurrentPath()).resolve(srcPath.getFileName().toString());

        try {

            Files.copy(srcPath, dstPath);
            dstPC.updateList(Paths.get(dstPC.getCurrentPath()));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Copy failed", ButtonType.OK);
            alert.showAndWait();
        }
    }


    public void btnMoveAction(ActionEvent actionEvent) {
        PanelController leftPC = (PanelController) leftPanel.getProperties().get("ctrl");
        PanelController rightPC = (PanelController) rightPanel.getProperties().get("ctrl");
        if (leftPC.getSelectedFilename() == null && rightPC.getSelectedFilename() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "File not selected", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        PanelController srcPC = null, dstPC = null;
        if (leftPC.getSelectedFilename() != null) {
            srcPC = leftPC;
            dstPC = rightPC;
        }
        if (rightPC.getSelectedFilename() != null) {
            srcPC = rightPC;
            dstPC = leftPC;
        }
        Path srcPath = Paths.get(srcPC.getCurrentPath(), srcPC.getSelectedFilename());
        Path dstPath = Paths.get(dstPC.getCurrentPath()).resolve(srcPath.getFileName().toString());

        try {
            Files.move(srcPath, dstPath);
            dstPC.updateList(Paths.get(dstPC.getCurrentPath()));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Move failed", ButtonType.OK);
            alert.showAndWait();
        }

    }

    public void btnDeleteAction(ActionEvent actionEvent) {
        PanelController leftPC = (PanelController) leftPanel.getProperties().get("ctrl");
        PanelController rightPC = (PanelController) rightPanel.getProperties().get("ctrl");
        if (leftPC.getSelectedFilename() == null && rightPC.getSelectedFilename() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "File not selected", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        PanelController srcPC = null, dstPC = null;
        if (leftPC.getSelectedFilename() != null) {
            srcPC = leftPC;
            dstPC = rightPC;
        }
        if (rightPC.getSelectedFilename() != null) {
            srcPC = rightPC;
            dstPC = leftPC;
        }
        Path srcPath = Paths.get(srcPC.getCurrentPath(), srcPC.getSelectedFilename());

        try {
            Files.delete(srcPath);
            srcPC.updateList(srcPath.getParent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}