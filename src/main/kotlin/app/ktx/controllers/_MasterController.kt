package app.ktx.controllers

import javafx.fxml.FXML;

import app.ktx.routes.Router;
import javafx.event.ActionEvent

open class _MasterController
{
    @FXML fun switchToHome(event: ActionEvent)
    {
        Router.switchScene(event, "home");
    }


    @FXML fun switchToMemberManaging(event: ActionEvent)
    {
        Router.switchScene(event, "member-managing");
    }


    @FXML fun switchToMemberForm(event: ActionEvent)
    {
        Router.switchScene(event, "member-form");
    }


    @FXML fun switchToRoomManaging(event: ActionEvent)
    {
        Router.switchScene(event, "room-managing");
    }


    @FXML fun switchToRoomForm(event: ActionEvent)
    {
        Router.switchScene(event, "room-form");
    }
}