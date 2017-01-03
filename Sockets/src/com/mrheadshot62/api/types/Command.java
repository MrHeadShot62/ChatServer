package com.mrheadshot62.api.types;

import java.io.Serializable;

/**
 * Created by novak on 04.01.2017.
 */
public class Command implements Serializable {
    private int command;
    private Object[] arg;

    public Command(int command, Object... arg) {
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
