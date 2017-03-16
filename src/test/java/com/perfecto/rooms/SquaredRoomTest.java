package com.perfecto.rooms;

import com.perfecto.Helpers.RoomHelper;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by marji on 2017-03-15.
 */
public class SquaredRoomTest extends RoomTest{

    @Test(expected = IllegalArgumentException.class)
    public void createRoomWithMinusLengthShouldThroughIllegalArgumentException() throws IllegalArgumentException {

        room = new SquaredRoom(RoomHelper.getRandomPoint(),RoomHelper.getRoomInvalidDimension());

    }

    @Test(expected = IllegalArgumentException.class)
    public void createRoomWithInvalidPointShouldThroughIllegalArgumentException() throws IllegalArgumentException {

        room = new SquaredRoom(new Point(6,6),RoomHelper.getRoomValidDimension());

    }
    @Test
    public void createRoomWithValidArgumentsShouldCreateRoom()  {

        final int ROOM_LENGTH = 5;
        SquaredRoom room = new SquaredRoom(RoomHelper.getRandomPoint(), ROOM_LENGTH);

        assertEquals(ROOM_LENGTH, room.length);
        assertEquals(RoomHelper.getRandomPoint(), room.getStartPosition());

    }
}