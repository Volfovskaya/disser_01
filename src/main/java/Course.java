public class Course {
    private int course_id;
    private String course_name;

    private int course_pc5_start;
    private int course_pc5_end;

    private int course_pc6_start;
    private int course_pc6_end;

    private int course_pc15_start;
    private int course_pc15_end;

    private int course_price;

    public int getCourse_id() {
        return course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public int getCourse_pc5_start() {
        return course_pc5_start;
    }

    public int getCourse_pc5_end() {
        return course_pc5_end;
    }

    public int getCourse_pc6_start() {
        return course_pc6_start;
    }

    public int getCourse_pc6_end() {
        return course_pc6_end;
    }

    public int getCourse_pc15_start() {
        return course_pc15_start;
    }

    public int getCourse_pc15_end() {
        return course_pc15_end;
    }

    public int getCourse_price() {
        return course_price;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public void setCourse_pc5_start(int course_pc5_start) {
        this.course_pc5_start = course_pc5_start;
    }

    public void setCourse_pc5_end(int course_pc5_end) {
        this.course_pc5_end = course_pc5_end;
    }

    public void setCourse_pc6_start(int course_pc6_start) {
        this.course_pc6_start = course_pc6_start;
    }

    public void setCourse_pc6_end(int course_pc6_end) {
        this.course_pc6_end = course_pc6_end;
    }

    public void setCourse_pc15_start(int course_pc15_start) {
        this.course_pc15_start = course_pc15_start;
    }

    public void setCourse_pc15_end(int course_pc15_end) {
        this.course_pc15_end = course_pc15_end;
    }

    public void setCourse_price(int course_price) {
        this.course_price = course_price;
    }

    public Course(int course_id, String course_name, int course_pc5_start, int course_pc5_end, int course_pc6_start, int course_pc6_end, int course_pc15_start, int course_pc15_end) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.course_pc5_start = course_pc5_start;
        this.course_pc5_end = course_pc5_end;
        this.course_pc6_start = course_pc6_start;
        this.course_pc6_end = course_pc6_end;
        this.course_pc15_start = course_pc15_start;
        this.course_pc15_end = course_pc15_end;
    }
}
