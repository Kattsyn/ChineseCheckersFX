package com.example.chinesecheckersfx;

/*
public String boardString =
            """
                    0000000000002000000000000
                    0000000000020200000000000
                    0000000000202020000000000
                    0000000002020202000000000
                    3030303010101010104040404
                    0303030101010101010404040
                    0030301010101010101040400
                    0003010101010101010104000
                    0000101010101010101010000
                    0006010101010101010105000
                    0060601010101010101050500
                    0606060101010101010505050
                    6060606010101010105050505
                    0000000007070707000000000
                    0000000000707070000000000
                    0000000000070700000000000
                    0000000000007000000000000""";

 */

public class Board {
    public String getBoardString() {
        return boardString;
    }

    public String getTwoPlayerBoard() {
        return twoPlayerBoard;
    }

    private final String boardString =
            """
                    0000000000002000000000000
                    0000000000020200000000000
                    0000000000202020000000000
                    0000000002020202000000000
                    3030303010101010104040404
                    0303030101010101010404040
                    0030301010101010101040400
                    0003010101010101010104000
                    0000101010101010101010000
                    0006010101010101010105000
                    0060601010101010101050500
                    0606060101010101010505050
                    6060606010101010105050505
                    0000000007070707000000000
                    0000000000707070000000000
                    0000000000070700000000000
                    0000000000007000000000000""";
    private final String twoPlayerBoard = """
            0000000000002000000000000
            0000000000020200000000000
            0000000000202020000000000
            0000000002020202000000000
            1010101010101010101010101
            0101010101010101010101010
            0010101010101010101010100
            0001010101010101010101000
            0000101010101010101010000
            0001010101010101010101000
            0010101010101010101010100
            0101010101010101010101010
            1010101010101010101010101
            0000000007070707000000000
            0000000000707070000000000
            0000000000070700000000000
            0000000000007000000000000""";

    public String boardToTestOrangeWin =
            """
                    0000000000002000000000000
                    0000000000070700000000000
                    0000000000707020000000000
                    0000000007070707000000000
                    3030303010101010704040404
                    0303030101010101070404040
                    0030301010101010101040400
                    0003010101010101010104000
                    0000101010101010101010000
                    0006010101010101010105000
                    0060601010101010101050500
                    0606060101010101010505050
                    6060606010202020205050505
                    0000000002020202000000000
                    0000000000101010000000000
                    0000000000010100000000000
                    0000000000001000000000000""";
}