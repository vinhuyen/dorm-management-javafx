package app.ktx.repositories

import app.ktx.models.Member
import app.ktx.services.DatabaseConnection
import java.sql.ResultSet;
import java.sql.Statement

object MemberRepository
{
    @JvmStatic val statement: Statement = DatabaseConnection.connection.createStatement();


    @JvmStatic fun getAllMembers(): ResultSet
    {
        val query: String =
            "SELECT m.id as id, m.full_name as full_name, m.room_id as room_id, r.name as room_name, m.joined_at as joined_at FROM `ktx_db`.`members` `m` " +
            "JOIN `ktx_db`.`rooms` `r` on `r`.`id` = `m`.`room_id`";
        val memberResultSet: ResultSet = RoomRepository.statement.executeQuery(query);

        return memberResultSet;
    }


//    @JvmStatic fun getConditionalMember(sortBy: String )
//    {
//        val sortCondition: String = "";
//        val sortQuery: String = "SORT BY ";
//    }

    @JvmStatic fun getSortedMembers(sortedColumn: String): ResultSet
    {
        val query: String =
            "SELECT m.id as id, m.full_name as full_name, m.room_id as room_id, r.name as room_name, m.joined_at as joined_at FROM `ktx_db`.`members` `m` " +
            "JOIN `ktx_db`.`rooms` `r` on `r`.`id` = `m`.`room_id` " +
            "ORDER BY $sortedColumn";
        return statement.executeQuery(query);
    }


    @JvmStatic fun deleteMember(member: Member)
    {
        val query: String =
            "DELETE FROM `ktx_db`.`members` as `m` " +
            "WHERE `m`.id = ${member.id}";

        statement.executeUpdate(query);
    }


    @JvmStatic fun addMember(member: Member)
    {
        val query: String =
            "INSERT INTO `ktx_db`.`members`(full_name, room_id, joined_at) " +
            "VALUES ('${member.fullName}', ${member.room.key}, '${member.joinedAt}') "

        statement.executeUpdate(query);
    }


    @JvmStatic fun getNumberOfMember(memberResultSet: ResultSet = getAllMembers()): UInt
    {
        var numberOfMember: UInt = 0u;

        while (memberResultSet.next())
        {
            numberOfMember ++;
        }

        return numberOfMember;
    }
}