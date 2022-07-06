package app.ktx.controllers

import app.ktx.models.Member
import app.ktx.repositories.MemberRepository
import app.ktx.utils.KeyValueComboBox
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.ComboBox
import javafx.scene.control.DatePicker
import javafx.scene.control.TextField
import java.net.URL
import java.time.LocalDate.*
import java.util.*
import java.sql.ResultSet
import java.sql.Date

import app.ktx.repositories.RoomRepository;
import javafx.event.ActionEvent

class MemberFormController: _MasterController(), Initializable
{
    @FXML private var fullNameField: TextField = TextField();
    @FXML private var roomComboBox: ComboBox<KeyValueComboBox> = ComboBox<KeyValueComboBox>();
    @FXML private lateinit var joinedDate: DatePicker;


    companion object
    {
        @JvmStatic var selectedMember: Member? = null;
    }


    override fun initialize(p0: URL?, p1: ResourceBundle?)
    {
        fillUpdateFrom(selectedMember);

        this.joinedDate = DatePicker(now());

        this.initRoomComboBox();
    }


    private fun fillUpdateFrom(selectedMember: Member?)
    {
        if (selectedMember == null) return;
        this.fullNameField.text = selectedMember.fullName;

        this.roomComboBox.items.add(selectedMember.room);
        this.roomComboBox.value = selectedMember.room;
        this.roomComboBox.promptText = selectedMember.room.toString();

        this.joinedDate.promptText = selectedMember.joinedAt.toString();
    }


    private fun initRoomComboBox()
    {
        val roomResultSet: ResultSet = RoomRepository.getAvailableRooms();

        this.roomComboBox.promptText = "pick a room";

        while (roomResultSet.next())
        {
            val key = roomResultSet.getInt("id");
            val value = roomResultSet.getString("name");
            this.roomComboBox.items.add(KeyValueComboBox(key, value));
        }
    }


    fun summit(event: ActionEvent)
    {
        val id: Int = 0;
        val fullName: String = this.fullNameField.text;
        val room: KeyValueComboBox =  this.roomComboBox.selectionModel.selectedItem;
        val joinedAt: Date = Date.valueOf(this.joinedDate.value);

        val newMember: Member = Member(id, fullName, room, joinedAt);

        if (selectedMember == null)
        {
            MemberRepository.addMember(newMember);
        }
        else
        {
            selectedMember?.updateItSelf(newMember);
        }

        selectedMember = null;
        super.switchToMemberManaging(event);
    }


    fun onCancel(event: ActionEvent)
    {
        selectedMember = null;
        super.switchToMemberManaging(event);
    }
}