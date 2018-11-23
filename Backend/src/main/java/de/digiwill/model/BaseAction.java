package de.digiwill.model;

import de.digiwill.SystemHandle;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

public class BaseAction {

    @Field("_id")
    protected ObjectId UID;
    protected boolean wasCompleted;
    protected ActionType type;

    public void execute(SystemHandle systemHandle){ }

    public ObjectId getUID() {
        return UID;
    }

    public ActionType getType() {
        return type;
    }
}
