public class MathCompetence {

    public static double getEffect(int pc5Employee, int pc6Employee, int pc15Employee,
                                   int pc5StartCourse, int pc6StartCourse, int pc15StartCourse,
                                   int pc5EndCourse, int pc6EndCourse, int pc15EndCourse,
                                   int price) {
        double pc5;
        double pc6;
        double pc15;
        double effect = 0;

        if (pc5Employee < pc5StartCourse |
                pc6Employee < pc6StartCourse |
                pc15Employee < pc15StartCourse) {
            return effect;
        } else {
            pc5 = pc5EndCourse - pc5Employee;
            pc6 = pc6EndCourse - pc6Employee;
            pc15 = pc15EndCourse - pc15Employee;

            if (pc5 <= 0) {
                pc5 = 0;
            }

            if (pc6 <= 0) {
                pc6 = 0;
            }

            if (pc15 <= 0) {
                pc15 = 0;
            }

            effect = (pc5 + pc6 + pc15);
            if (effect == 0) {
                return effect;
            }

//            return effect;
            effect = effect / price;
            return effect;

        }
    }

}
