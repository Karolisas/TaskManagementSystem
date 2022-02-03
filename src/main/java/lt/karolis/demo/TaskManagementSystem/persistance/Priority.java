package lt.karolis.demo.TaskManagementSystem.persistance;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Priority {

    TO_DO(10),
    IN_PROGRESS(20),
    DONE(30);

    private final int priorityNo;

    Priority(int i) {
        priorityNo = i;
    }

    public static Priority getPriorityByCOde(int dbData) {
        return PRIORITY_MAP.get(dbData);
    }

    public int getPriorityNo() {
        return priorityNo;
    }

    static Map<Integer, Priority> PRIORITY_MAP = new HashMap<>();

    static {
        for (Priority priority : values())
            PRIORITY_MAP.put(priority.getPriorityNo(), priority);

        Arrays.stream(values()).forEach(v -> PRIORITY_MAP.put(v.getPriorityNo(), v));
    }

}
