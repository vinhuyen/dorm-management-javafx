package app.ktx.controllers

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Label
import java.net.URL
import java.util.*

import app.ktx.repositories.RoomRepository;
import app.ktx.repositories.MemberRepository;

class HomeController: _MasterController(), Initializable
{
    @FXML private var numberOfRoom: Label = Label();
    @FXML private var capacity: Label = Label();
    @FXML private var totalCurrentMember: Label = Label();


    override fun initialize(p0: URL?, p1: ResourceBundle?)
    {
        this.numberOfRoom.text = RoomRepository.getNumberOfRoom().toString();
        this.capacity.text = RoomRepository.getCapacity().toString();
        this.totalCurrentMember.text = MemberRepository.getNumberOfMember().toString();
    }
}