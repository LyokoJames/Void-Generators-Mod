package lj.vgm.core.util;

public enum ConduitState {
    DISCONNECTED,
    INPUT,
    OUTPUT;
    
    public boolean isConnected() {
        return this != DISCONNECTED;
    }
    
    public boolean isInput() {
        return this == INPUT;
    }
    
    public static ConduitState fromInt(int i) {
        if (i < 3)
            return ConduitState.values()[i];
        else return DISCONNECTED;
    }
}
