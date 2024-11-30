package com.chinesecheckersfx;



public class MoveResult {

    private final MoveType type;

    public MoveType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoveResult that = (MoveResult) o;
        return type == that.type;
    }


    public MoveResult(MoveType type) {
        this.type = type;
    }
}
