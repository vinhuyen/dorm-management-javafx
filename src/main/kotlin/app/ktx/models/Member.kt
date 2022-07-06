package app.ktx.models

import app.ktx.services.DatabaseConnection
import app.ktx.utils.KeyValueComboBox
import java.sql.Date
import java.sql.Statement


class Member
(
    val id: Int,
    var fullName: String,
    var room: KeyValueComboBox,
    var joinedAt: Date
)
{
    companion object
    {
        @JvmStatic val statement: Statement = DatabaseConnection.connection.createStatement();
    }

    fun updateItSelf(member: Member)
    {
        val query: String =
            "UPDATE `ktx_db`.`members` m " +
            "SET m.room_id = ${member.room.key}, m.full_name = '${member.fullName}', m.joined_at = '${member.joinedAt}' " +
            "WHERE m.id = ${this.id}";
        statement.executeUpdate(query);
    }
}