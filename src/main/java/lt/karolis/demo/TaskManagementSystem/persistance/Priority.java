package lt.karolis.demo.TaskManagementSystem.persistance;

import javax.persistence.Entity;

public enum Priority {

    TO_DO(10),
    IN_PROGRESS(20),
    DONE(30);

    private final int priorityNo;

    Priority(int i) {
        priorityNo = i;
    }
}
