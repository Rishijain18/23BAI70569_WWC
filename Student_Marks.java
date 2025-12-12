import java.util.*;

class Student {
    private String id;
    private String name;
    private int marks;

    public Student(String id, String name, int marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMarks() {
        return marks;
    }

    public String getRole() {
        return "Undergrad";
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks + ", Role: " + getRole();
    }
}
class Teacher {
    private String id;
    private String name;
    private String subject;

    public Teacher(String id, String name, String subject) {
        this.id = id;
        this.name = name;
        this.subject = subject;
    }
    public void createReport(Student s) {
        System.out.println("\n===== STUDENT REPORT =====");
        System.out.println("Teacher : " + name);
        System.out.println("Subject : " + subject);
        System.out.println("Student Name : " + s.getName());
        System.out.println("Marks        : " + s.getMarks());
    }
}
class GraduateStudent extends Student {
    private String area;

    public GraduateStudent(String id, String name, int marks, String area) {
        super(id, name, marks);
        this.area = area;
    }
    @Override
    public String getRole() {
        return "Grad (" + area + ")";
    }
}

class Repository<T> {
    private Map<String, T> data = new HashMap<>();

    public void save(String id, T obj) {
        data.put(id, obj);
    }

    public T find(String id) {
        return data.get(id);
    }

    public void delete(String id) {
        data.remove(id);
    }
}

public class Main {
    public static void main(String[] args) {

        List<Student> list = new ArrayList<>();
        list.add(new Student("S1", "A", 85));
        list.add(new Student("S2", "B", 95));
        list.add(new GraduateStudent("G1", "X", 90, "CSE"));

        // Create repo FIRST
        Repository<Student> repo = new Repository<>();
        for (Student s : list) {
            repo.save(s.getId(), s);
        }

        Teacher t1 = new Teacher("T1", "Mr.Jain", "Aptitude");
        Student s = repo.find("S2");

        if (s != null) {
            t1.createReport(s);
        }

        System.out.println("All:");
        list.forEach(System.out::println);

        System.out.println("\nFind S2:");
        Student found = repo.find("S2");
        System.out.println(found != null ? found : "Not Found");
        Iterator<Student> it = list.iterator();
        while (it.hasNext()) {
            Student st = it.next();
            if (st.getMarks() < 80) {
                it.remove();
                repo.delete(st.getId());
            }
        }

        System.out.println("\nAfter Removal:");
        list.forEach(System.out::println);
    }
}
