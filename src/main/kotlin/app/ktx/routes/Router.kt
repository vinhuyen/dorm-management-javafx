package app.ktx.routes

import app.ktx.HelloApplication
import javafx.event.ActionEvent
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Scene
import javafx.stage.Stage

object Router
{
    @JvmStatic val routes: Map<String, String> = mapOf(
        "home" to "home",
        "member-managing" to "member-managing",
        "member-form" to "member-form",
        "room-managing" to "room-managing",
        "room-form" to "room-form"
    );


    @JvmStatic fun switchScene(event: ActionEvent, page: String)
    {

        try
        {
            val fxmlLoader: FXMLLoader = FXMLLoader(HelloApplication::class.java.getResource("${routes[page]}.fxml"));

            val scene = Scene(fxmlLoader.load());
            val stage: Stage = (event.source as Node).scene.window as Stage;

            stage.title = page;
            stage.scene = scene;
            stage.show();
        }
        catch(error: java.lang.Exception)
        {
            error.printStackTrace();
        }
    }
}