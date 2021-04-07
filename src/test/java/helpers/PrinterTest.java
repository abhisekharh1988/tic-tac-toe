package helpers;

import exceptions.FatalException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class PrinterTest {

    @Test
    public void shouldThrowExceptionIfInvalidStringIsPassed() {
        FatalException thrown = assertThrows(FatalException.class, () -> new Printer().printBoard("1"));

        assertEquals("Invalid Result - Printer Cannot print", thrown.getMessage());
    }

    @Data
    @AllArgsConstructor
    class University{
        private List<Student> students;
    }

    @Data
    @AllArgsConstructor
    class Student{
        private String name;
        private int roll;
        private boolean active;
    }

    @Test
    public void shouldNotMutate() {
        List<Student> students = List.of(
                new Student("Abhisek", 1, false),
                new Student("Srujan", 1, false),
                new Student("Vasanth", 1, false)
        );

        University university = new University(students);

        List<Student> updatedStudents = university.getStudents().stream()
                .peek(this::update)
                .collect(Collectors.toList());

        University newUniversity = new University(updatedStudents);

        System.out.println("Previous University - " + university);
        System.out.println("Current University - " + newUniversity);

        assertFalse(university.getStudents().get(0).isActive());
        assertTrue(newUniversity.getStudents().get(0).isActive());
    }

    private Student update(Student student) {

        return new Student(student.getName(), student.getRoll(), true);
    }

}