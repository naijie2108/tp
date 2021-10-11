package task;

import command.Parser;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;


public class TaskListTest {
    @Test
    void get_success() {
        TaskList a = new TaskList();
        a.addTask("cs1010","Read Book A /by 12/12/2021 1600");
        a.addTask("cs1010","Read Book B /by 13/12/2021 1600");
        assertEquals("[ ] Read Book A by: 12 Dec 2021 04:00 PM" , a.get(0).toString());
        assertEquals("[ ] Read Book B by: 13 Dec 2021 04:00 PM" , a.get(1).toString());
    }

    @Test
    void addTask_success() {
        TaskList tasks = new TaskList();
        tasks.addTask("cs1010", "Read Book /by 12/12/2021 1600");
        assertEquals("[ ] Read Book by: 12 Dec 2021 04:00 PM", tasks.get(0).toString());
    }

    @Test
    void getTaskCount_success() {
        TaskList tasks = new TaskList();
        tasks.addTask("cs1010", "Read Book /by 12/12/2021 1600");
        tasks.addTask("cs1010", "Return Book /by 13/12/2021 1600");
        assertEquals(2, tasks.getTaskCount());
    }

    @Test
    void getTaskList_success() {
        TaskList a = new TaskList();
        TaskList b = new TaskList();
        a.addTask("cs1010","Read Book A /by 12/12/2021 1600");
        a.addTask("cs1010","Read Book B /by 13/12/2021 1600");
        b.addTask("cs1010", "Buy Book A /by 14/12/2021 1600");
        b.addTask("cs1010", "Buy Book B /by 15/12/2021 1600");
        ArrayList<Task> list = new ArrayList<>();
        list.addAll(a.getTaskList());
        list.addAll(b.getTaskList());
        assertEquals(4, list.size());
        assertEquals("[ ] Read Book A by: 12 Dec 2021 04:00 PM", list.get(0).toString());
        assertEquals("[ ] Read Book B by: 13 Dec 2021 04:00 PM", list.get(1).toString());
        assertEquals("[ ] Buy Book A by: 14 Dec 2021 04:00 PM", list.get(2).toString());
        assertEquals("[ ] Buy Book B by: 15 Dec 2021 04:00 PM", list.get(3).toString());
    }

    @Test
    void setTaskList_success() {
        TaskList a = new TaskList();
        TaskList b = new TaskList();
        a.addTask("cs1010","Read Book A /by 12/12/2021 1600");
        a.addTask("cs1010","Read Book B /by 13/12/2021 1600");
        b.addTask("cs1010", "Buy Book A /by 14/12/2021 1600");
        b.addTask("cs1010", "Buy Book B /by 15/12/2021 1600");
        a.setTaskList(b.getTaskList());
        assertEquals(2, a.getTaskCount());
        assertEquals("[ ] Buy Book A by: 14 Dec 2021 04:00 PM", a.get(0).toString());
        assertEquals("[ ] Buy Book B by: 15 Dec 2021 04:00 PM", a.get(1).toString());

    }

    @Test
    void setTaskCount_success() {
        TaskList a = new TaskList();
        a.setTaskCount(10);
        assertEquals(10, a.getTaskCount());
    }

    @Test
    void delete_success() {
        TaskList a = new TaskList();
        a.addTask("cs1010","Read Book A /by 12/12/2021 1600");
        a.addTask("cs1010","Read Book B /by 13/12/2021 1600");
        assertEquals("[ ] Read Book A by: 12 Dec 2021 04:00 PM", a.get(0).toString());
        assertEquals("[ ] Read Book B by: 13 Dec 2021 04:00 PM", a.get(1).toString());
        a.delete(a.get(0));
        assertEquals("[ ] Read Book B by: 13 Dec 2021 04:00 PM", a.get(0).toString());
    }

    @Test
    void printTaskList_success() {
        TaskList a = new TaskList();
        a.addTask("cs1010","Read Book A /by 12/12/2021 1600");
        a.addTask("cs1010","Read Book B /by 13/12/2021 1600");
        // Create a stream to hold the output
        ByteArrayOutputStream read = new ByteArrayOutputStream();
        PrintStream save = new PrintStream(read);
        // Tell Java to use your special stream
        System.setOut(save);
        // Print some output: goes to your special stream
        a.printTaskList("Read");
        List<String> actualLines = List.of(read.toString().split("/n"));
        List<String> expectedLines = Collections.singletonList("The tasks in Read are: " + System.lineSeparator()
                + "1.[ ] Read Book A by: 12 Dec 2021 04:00 PM" + System.lineSeparator()
                + "2.[ ] Read Book B by: 13 Dec 2021 04:00 PM" + System.lineSeparator());
        assertLinesMatch(expectedLines, actualLines);
    }
}
