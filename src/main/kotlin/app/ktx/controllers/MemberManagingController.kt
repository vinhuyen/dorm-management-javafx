package app.ktx.controllers

import javafx.fxml.FXML;
import javafx.fxml.Initializable
import javafx.scene.control.TableView
import java.net.URL
import java.util.*
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.scene.control.ComboBox
import javafx.scene.control.TableColumn
import javafx.scene.control.cell.PropertyValueFactory
import java.sql.ResultSet

import app.ktx.models.Member;
import app.ktx.repositories.MemberRepository
import app.ktx.utils.KeyValueComboBox
import javafx.event.ActionEvent
import javafx.scene.control.Button


class MemberManagingController: _MasterController(), Initializable
{
    @FXML private lateinit var memberTable: TableView<Member>;

    @FXML private lateinit var idColumn: TableColumn<Member, Int>;
    @FXML private lateinit var fullNameColumn: TableColumn<Member, String>;
    @FXML private lateinit var roomNameColumn: TableColumn<Member, String>;
    @FXML private lateinit var joinedAtColumn: TableColumn<Member, Date>;

    @FXML private var sortByComboBox: ComboBox<KeyValueComboBox> = ComboBox();

    @FXML private var removeButton: Button = Button();

    private val memberObservableList: ObservableList<Member> = FXCollections.observableArrayList<Member?>();


    override fun initialize(p0: URL?, p1: ResourceBundle?)
    {
        this.initSortByComboBox();

        this.initMemberTable();

        this.setObservableMemberList(MemberRepository.getAllMembers());
    }


    private fun initSortByComboBox()
    {
        this.sortByComboBox.promptText = "none";

        this.sortByComboBox.items.addAll(
            KeyValueComboBox("m.id", "id"),
            KeyValueComboBox("m.full_name", "name"),
            KeyValueComboBox("r.name", "room"),
            KeyValueComboBox("m.joined_at", "joined date")
        );
    }


    private fun initMemberTable()
    {
        this.idColumn.cellValueFactory = PropertyValueFactory<Member, Int>("id");
        this.fullNameColumn.cellValueFactory = PropertyValueFactory<Member, String>("fullName");
        this.roomNameColumn.cellValueFactory = PropertyValueFactory<Member, String>("room");
        this.joinedAtColumn.cellValueFactory = PropertyValueFactory<Member, Date>("joinedAt");

        this.setMemberTable(this.memberObservableList);
    }


    private fun setMemberTable(members: ObservableList<Member>): Unit
    {
        this.memberTable.items = this.memberObservableList;
    }


    fun onComboBoxSelection(event: ActionEvent)
    {
        val sortedColumn = this.sortByComboBox.selectionModel.selectedItem.key.toString()
        this.setObservableMemberList(MemberRepository.getSortedMembers(sortedColumn))
    }


    private fun setObservableMemberList(resultSet: ResultSet)
    {
        this.memberObservableList.clear();

        val memberResultSet: ResultSet = resultSet;

        while (memberResultSet.next())
        {
            val room: KeyValueComboBox = KeyValueComboBox(
                memberResultSet.getInt("room_id"),
                memberResultSet.getString("room_name")
            )
            val member: Member = Member(
                memberResultSet.getInt("id"),
                memberResultSet.getString("full_name"),
                room,
                memberResultSet.getDate("joined_at")
            );

            this.memberObservableList.add(member);
        }
    }


    @FXML fun deleteMemberFromTable(event: ActionEvent)
    {
        val selectedMember: Member? = this.memberTable.selectionModel.selectedItem;

        if (selectedMember === null) { return }

        this.memberObservableList.remove(selectedMember);

        MemberRepository.deleteMember(selectedMember);
    }


    @FXML fun onEditMember(event: ActionEvent)
    {
        val selectedMember: Member? = this.memberTable.selectionModel.selectedItem;

        if (selectedMember === null) { return }

        MemberFormController.selectedMember = selectedMember;

        super.switchToMemberForm(event);
    }

}