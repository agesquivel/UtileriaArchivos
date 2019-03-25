package itchetumal.edu.mx.tap.utilerias.archivos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main extends Application {

    private File selectedDirectory;
    private File listaArchsSelecccionada;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX App");

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));

        Button btnDirectorio = new Button("Seleccionar Directorio");
        Label lblDirectorio = new Label("Carpeta destino");

        btnDirectorio.setOnAction(e -> {
            selectedDirectory = directoryChooser.showDialog(primaryStage);

            lblDirectorio.setText(selectedDirectory.getAbsolutePath());
        });


        HBox hbxDirectorio = new HBox(btnDirectorio, lblDirectorio);

        FileChooser fileChooser = new FileChooser();

        Button btnListaArchs = new Button("Seleccionar lista de carpetas");
        Label lblListaArchs = new Label("Lista de carpetas a crear");

        btnListaArchs.setOnAction(e -> {
            fileChooser.setInitialDirectory(new File(selectedDirectory.getAbsolutePath()));

            listaArchsSelecccionada = fileChooser.showOpenDialog(primaryStage);

            lblListaArchs.setText(listaArchsSelecccionada.getAbsoluteFile().toString());
        });

        HBox hbxListaArchs = new HBox(btnListaArchs, lblListaArchs);

        //Crear carpetas usando la lista de nombrescargada
        if (new File(listaArchsSelecccionada.getAbsoluteFile().toString()).exists()) {
            crearCarpetas(listaArchsSelecccionada.getAbsoluteFile().toString());

        }

        VBox vBox = new VBox(hbxDirectorio, hbxListaArchs);

        Scene scene = new Scene(vBox, 960, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void leerArchivo(String archivo) {
        BufferedReader br = null;
        String text = "";
        try {
            File fileTemp = new File(archivo);
            br = new BufferedReader(new FileReader(fileTemp));
            String linea = "";
            while ((linea = br.readLine()) != null) {
                if (!linea.isEmpty()) {
                    text += linea += "\n";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != br) {
                    br.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        System.out.println("Texto en el archivo: \n" + text);
    } //leerArchivo

    private void crearCarpetas(String ruta){
        File archivo = new File(ruta + "/archivo1.txt");

        leerArchivo(ruta + "/archivo1.txt");

    }

    public static void main(String[] args) {
        launch(args);
    }
}
