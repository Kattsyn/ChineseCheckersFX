package com.example.chinesecheckersfx;

public enum WinChecker {
    NONE {
        @Override
        public boolean checkWin(Cell[][] board) {
            return false;
        }
    },
    WHITE {
        @Override
        public boolean checkWin(Cell[][] board) {
            return false;
        }
    },
    RED {
        @Override
        public boolean checkWin(Cell[][] board) {
            for (int y = 13; y < 17; y++) {
                for (int x = 0; x < board[0].length; x++) {
                    if (board[y][x] != null
                            && (board[y][x].getChecker() == null
                            || board[y][x].getChecker().getType() != CheckerType.RED)) {
                        return false;
                    }
                }
            }
            return true;
        }
    },
    GREEN {
        @Override
        public boolean checkWin(Cell[][] board) {
            int i = 0;
            for (int y = 12; y >= 9; y--) {
                for (int x = 17 + i; x <= board[y].length; x++) {
                    if (board[y][x] != null
                            && (board[y][x].getChecker() == null
                            || board[y][x].getChecker().getType() != CheckerType.GREEN)) {
                        return false;
                    }
                }
                i++;
            }
            return true;
        }
    },
    YELLOW {
        @Override
        public boolean checkWin(Cell[][] board) {
            int i = 0;
            for (int y = 12; y >= 9; y--) {
                for (int x = 7 - i; x >= 0; x--) {
                    if (board[y][x] != null
                            && (board[y][x].getChecker() == null
                            || board[y][x].getChecker().getType() != CheckerType.YELLOW)) {
                        return false;
                    }
                }
                i++;
            }
            return true;
        }
    },
    BLUE {
        @Override
        public boolean checkWin(Cell[][] board) {
            int i = 0;
            for (int y = 4; y <= 7; y++) {
                for (int x = 7 - i; x >= 0; x--) {
                    if (board[y][x] != null
                            && (board[y][x].getChecker() == null
                            || board[y][x].getChecker().getType() != CheckerType.BLUE)) {
                        return false;
                    }
                }
            }
            return true;
        }
    },
    PURPLE {
        @Override
        public boolean checkWin(Cell[][] board) {
            int i = 0;
            for (int y = 4; y <= 7; y++) {
                for (int x = 17 + i; x <= board[y].length; x++) {
                    if (board[y][x] != null
                            && (board[y][x].getChecker() == null
                            || board[y][x].getChecker().getType() != CheckerType.PURPLE)) {
                        return false;
                    }
                }
                i++;
            }
            return true;
        }
    },
    ORANGE {
        @Override
        public boolean checkWin(Cell[][] board) {
            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < board[0].length; x++) {
                    if (board[y][x] != null
                            && (board[y][x].getChecker() == null
                            || board[y][x].getChecker().getType() != CheckerType.ORANGE)) {
                        return false;
                    }
                }
            }
            return true;
        }
    };

    public abstract boolean checkWin(Cell[][] board);
}
