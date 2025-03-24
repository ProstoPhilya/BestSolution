package org.owlTeam.entityClass;

import java.io.Serializable;

public abstract class Basic implements Serializable, Comparable<Basic> {

    public abstract void validate();
    public abstract int getIntValue();
}
