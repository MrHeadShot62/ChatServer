package com.mrheadshot62.api.types.answer;

import com.mrheadshot62.api.TypesAnswer;

/**
 * Created by DmitriyRoot on 07.01.2017.
 */
public class ServerAnswerPacket extends CoreServerAnswerPacket {

    private final int serverAnswerCode;
    private final int type;
    private final Object objects;

    public ServerAnswerPacket(int serverAnswerCode, int type, Object objects) {
        this.serverAnswerCode = serverAnswerCode;
        this.type = type;
        this.objects = objects;
    }

    public ServerAnswerPacket(int serverAnswerCode) {
        this.serverAnswerCode = serverAnswerCode;
        this.type = TypesAnswer.ONLYCODE;
        this.objects = null;
    }

    public int getServerAnswerCode() {
        return serverAnswerCode;
    }

    public int getType() {
        return type;
    }

    public Object getObjects() {
        return objects;
    }
}
