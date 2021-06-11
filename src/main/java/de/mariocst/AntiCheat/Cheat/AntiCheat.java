package de.mariocst.AntiCheat.Cheat;

import cn.nukkit.Player;

public abstract class AntiCheat {
    protected Player player;

    public AntiCheat(Player player) {
        this.player = player;
    }

    abstract public CheatType getCheatType();

    abstract public boolean isCheat();


    public enum CheatType {
        INVALID_ITEM("invaliditem");

        private String typeName;

        CheatType(String typeName) {
            this.typeName = typeName;
        }

        public static CheatType fromTypeName(String typeName) {
            for (CheatType type : CheatType.values()) {
                if (type.getTypeName().equals(typeName)) {
                    return type;
                }
            }
            return null;
        }

        public String getTypeName() {
            return this.typeName;
        }

        @Override
        public String toString() {
            return this.getTypeName();
        }

    }
}
