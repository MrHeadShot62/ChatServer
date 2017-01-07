package com.mrheadshot62.api.types.answer;

import com.mrheadshot62.api.TypesAnswer;

/**
 * Created by DmitriyRoot on 07.01.2017.
 */
public class ServerAnswerPacket extends CoreServerAnswerPacket {

    public final int serverAnswerCode;
    public final int type;
    public final Object objects;

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
}
