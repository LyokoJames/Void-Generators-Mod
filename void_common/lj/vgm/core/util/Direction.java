package lj.vgm.core.util;

public enum Direction {
    NORTH(0,0,-1,2),
    SOUTH(0,0,1,3),
    EAST(1,0,0,5),
    WEST(-1,0,0,4),
    UP(0,1,0,1),
    DOWN(0,-1,0,0),
    STATIC(0,0,0,-1);
    
    public final int x;
    public final int y;
    public final int z;
    public final int side;
    
    private Direction(int _x,int _y,int _z,int _side) {
        x = _x;
        y = _y;
        z = _z;
        side = _side;
    }
    
    public static Direction sideToDir(int _side) {
        for (Direction dir : Direction.values()) {
            if (dir.side == _side) return dir;
        }
        return STATIC;
    }
}
