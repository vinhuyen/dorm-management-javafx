package app.ktx.repositories

import java.sql.ResultSet
import java.sql.Statement

import app.ktx.services.DatabaseConnection as DB;

object RoomRepository
{
    @JvmStatic val statement: Statement = DB.connection.createStatement();


    @JvmStatic fun getAvailableRooms(): ResultSet
    {
        val query: String =
            "SELECT * FROM ktx_db.rooms r " +
            "WHERE r.capacity > (SELECT COUNT(*) FROM ktx_db.members m WHERE r.id = m.room_id)";
        val roomResultSet: ResultSet = statement.executeQuery(query);

        return roomResultSet;
    }


    @JvmStatic fun getAllRooms(): ResultSet
    {
        val query: String =
            "SELECT r.*, COUNT(m.id) as member_amount FROM `ktx_db`.`rooms` r " +
            "JOIN ktx_db.members m on r.id = m.room_id " +
            "WHERE r.id = m.room_id " +
            "GROUP BY r.id;";
        val roomResultSet: ResultSet = statement.executeQuery(query);

        return roomResultSet;
    }


    @JvmStatic fun getCapacity(roomResultSet: ResultSet = getAllRooms()): UInt
    {
        var totalCapacity: UInt = 0u;

        while (roomResultSet.next())
        {
            totalCapacity += roomResultSet.getInt("capacity").toUInt()
        }

        return totalCapacity;
    }


    @JvmStatic fun getNumberOfRoom(roomResultSet: ResultSet = getAllRooms()): UInt
    {
        var numberOfRoom: UInt = 0u;

        while (roomResultSet.next()) { numberOfRoom ++ }

        return numberOfRoom;
    }
}