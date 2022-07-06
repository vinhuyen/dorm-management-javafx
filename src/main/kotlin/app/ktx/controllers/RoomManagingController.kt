package app.ktx.controllers

import app.ktx.models.Member
import app.ktx.utils.KeyValueComboBox
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.ComboBox
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import java.net.URL
import java.util.*
import app.ktx.models.Room
import app.ktx.repositories.MemberRepository
import app.ktx.repositories.RoomRepository
import javafx.event.ActionEvent
import javafx.scene.control.cell.PropertyValueFactory
import java.sql.ResultSet

class RoomManagingController: _MasterController(), Initializable
{
    @FXML private lateinit var roomTable: TableView<Room>;

    @FXML private lateinit var idColumn: TableColumn<Room, Int>;
    @FXML private lateinit var nameColumn: TableColumn<Room, String>;
    @FXML private lateinit var capacityColumn: TableColumn<Room, Int>;
    @FXML private lateinit var memberAmountColumn: TableColumn<Room, Int>;

//    @FXML private var sortByComboBox: ComboBox<KeyValueComboBox> = ComboBox();

    private val roomObservableList: ObservableList<Room> = FXCollections.observableArrayList<Room?>();

    override fun initialize(p0: URL?, p1: ResourceBundle?)
    {
        this.initRoomTable();
        this.setObservableRoomList(RoomRepository.getAllRooms());
    }


    private fun initRoomTable()
    {
        this.idColumn.cellValueFactory = PropertyValueFactory<Room, Int>("id");
        this.nameColumn.cellValueFactory = PropertyValueFactory<Room, String>("name");
        this.capacityColumn.cellValueFactory = PropertyValueFactory<Room, Int>("capacity");
        this.memberAmountColumn.cellValueFactory = PropertyValueFactory<Room, Int>("memberAmount");

        this.roomTable.items = this.roomObservableList;
    }


    private fun setObservableRoomList(resultSet: ResultSet)
    {
        this.roomObservableList.clear();

        val roomResultSet: ResultSet = resultSet;

        while (roomResultSet.next())
        {
            val room: Room = Room(
                roomResultSet.getInt("id"),
                roomResultSet.getString("name"),
                roomResultSet.getInt("capacity"),
                roomResultSet.getInt("member_amount")
            );

            this.roomObservableList.add(room);
        }
    }
}