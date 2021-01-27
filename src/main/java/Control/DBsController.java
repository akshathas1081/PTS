package Control;

import Model.DBmanager;
import com.ibatis.common.jdbc.ScriptRunner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.spi.FileSystemProvider;
import java.sql.SQLException;
import java.util.Collections;

public class DBsController {

    private DBmanager dbm;

    @FXML
    TextField tfUser;
    @FXML
    PasswordField pf;
    @FXML
    TextField tfPort;
    @FXML
    Button connectBtn;
    @FXML
    Button createBtn;
    @FXML
    Button insertBtn;
    @FXML
    Button appBtn;

    public void connectBtn(ActionEvent event) {
        String user = tfUser.getText();
        String password = pf.getText();
        int port = Integer.valueOf(tfPort.getText());
        DBmanager.setInstance(new DBmanager(user, password, port));
        dbm = DBmanager.getInstance();
        if (dbm.connect()) {
            createBtn.setDisable(false);
            tfUser.setEditable(false);
            pf.setEditable(false);
            tfPort.setEditable(false);
            connectBtn.setDisable(true);
        }
        if (dbm.dbExists()) {
            createBtn.setDisable(true);
            appBtn.setDisable(false);
        }
    }

    public void createBtn(ActionEvent event) {
        if(runScript("DataBaseCreation.sql")) {
            insertBtn.setDisable(false);
            createBtn.setDisable(true);
        }
    }

    public void insertBtn(ActionEvent event) {
        boolean ctrl = true;
        int i = 0;
        while (ctrl && i < 6) {
            ctrl = runScript("DataGeneration_" + i++ + ".sql");
        }

        if(ctrl) {
            appBtn.setDisable(false);
            insertBtn.setDisable(true);
        }
    }

    public void appBtn(ActionEvent event) {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/View/Login.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = Launcher.getStage();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public boolean runScript(String scriptName) {
        URI uri = null;
        try {
            uri = getClass().getClassLoader().getResource("Scripts/" + scriptName).toURI();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return false;
        }

        // Solution to jar problem
        if("jar".equals(uri.getScheme())){
            for (FileSystemProvider provider: FileSystemProvider.installedProviders()) {
                if (provider.getScheme().equalsIgnoreCase("jar")) {
                    try {
                        provider.getFileSystem(uri);
                    } catch (FileSystemNotFoundException e) {
                        try {
                            provider.newFileSystem(uri, Collections.emptyMap());
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }

        Path path = Paths.get(uri);

        // new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(path.toString())))

        ScriptRunner runner = new ScriptRunner(dbm.getCon(), false, false);
        try {
            runner.runScript(Files.newBufferedReader(path));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}