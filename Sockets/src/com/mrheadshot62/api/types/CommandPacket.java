package com.mrheadshot62.api.types;

import java.io.Serializable;

/**
 * Created by novak on 04.01.2017.
 */
public class CommandPacket implements Serializable {
    private final int command;
    private final Object[] arg;

    public CommandPacket(int command, Object... arg) {
        this.command = command;
        this.arg = arg;
    }

    public int getCommand() {
        return command;
    }

    public Object[] getArg() {
        return arg;
    }
}
