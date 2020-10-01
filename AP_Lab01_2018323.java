import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Placement {


    static class Companies{
        public static void Q1AddCompany(String name, ArrayList<String> course, int nu_req_stu, HashMap<String, ArrayList<String>> comp, HashMap<String, Integer> stud, HashMap<String, Integer> branch_of_student,HashMap<Integer, ArrayList<String>> Applies_Comp,HashMap<Integer, ArrayList<Integer>> marks, HashMap<Integer, Integer> marks_tech) {
            Scanner scrn = new Scanner(System.in);
            comp.put(name, course);
            stud.put(name, nu_req_stu);
            System.out.println(name);
            System.out.println("Course Criteria");
            for (int i = 0; i < course.size(); i++) {
                System.out.println(course.get(i));
            }
            System.out.println("Number Of Required Students ="+nu_req_stu);
            System.out.println("Application Status = OPEN");
            System.out.println("Enter scores for the technical round.");
            for (int j = 0; j < course.size(); j++) {
                String co = course.get(j);
                System.out.println("Enter score for Roll no." + " " + branch_of_student.get(co));
                int mark = scrn.nextInt();
                if(marks.containsKey(branch_of_student.get(co))){
                    ArrayList<Integer> arr = marks.get(branch_of_student.get(co));
                    arr.add(mark);
                    marks.put(branch_of_student.get(co),arr);
                }else{
                    ArrayList<Integer> arr = new ArrayList<>();
                    arr.add(mark);
                    marks.put(branch_of_student.get(co),arr);
                }
                if(Applies_Comp.containsKey(branch_of_student.get(co))){
                    ArrayList<String> arr = Applies_Comp.get(branch_of_student.get(co));
                    arr.add(name);
                    Applies_Comp.put(branch_of_student.get(co),arr);

                }else{
                    ArrayList<String> arr = new ArrayList<>();
                    arr.add(name);
                    Applies_Comp.put(branch_of_student.get(co),arr);

                }
                marks_tech.put(branch_of_student.get(co),mark);


            }


        }

        public static void Q3RmComp(HashMap<String, Boolean> IsClosed) {
            ArrayList<String> check = new ArrayList<>();
            Set<String> keys = IsClosed.keySet();
            for (String key : keys) {
                check.add(key);
            }
            for (int i = 0; i < check.size(); i++) {
                if (IsClosed.get(check.get(i))) {
                    System.out.println("Company removed = " + check.get(i));
                }
            }

        }
        public static void Q5OpenComp(HashMap<String, Boolean> IsClosed) {
            ArrayList<String> check = new ArrayList<>();
            Set<String> keys = IsClosed.keySet();
            for (String key : keys) {
                check.add(key);
            }
            for (int i = 0; i < check.size(); i++) {
                if (!IsClosed.get(check.get(i))) {
                    System.out.println(check.get(i));
                }
            }

        }
        public static void Q7Display_comp(String Comp_name, HashMap<String, Boolean> IsClosed, HashMap<String, ArrayList<String>> comp, HashMap<String, Integer> stud) {
            if (comp.containsKey(Comp_name)) {
                System.out.println(Comp_name);
                System.out.println("Course Criteria");
                ArrayList<String> cou = comp.get(Comp_name);
                for (int i = 0; i < cou.size(); i++) {
                    System.out.println(cou.get(i));
                }
                System.out.println("Number Of Required Students =" + " " + stud.get(Comp_name));
                if (IsClosed.get(Comp_name)) {
                    System.out.println("Application Status = CLOSED");
                } else {
                    System.out.println("Application Status = OPEN");
                }
            }

        }

    }

    static class Student{
        public static void Q2PlacedStu(HashMap<Integer, Boolean> Isplaced) {
            ArrayList<Integer> rollno = new ArrayList<>();
            Set<Integer> keys = Isplaced.keySet();
            for (Integer key : keys) {
                rollno.add(key);
            }
            for (int i = 0; i < rollno.size(); i++) {
                if (Isplaced.get(rollno.get(i))) {
                    System.out.println("Accounts removed for" + " " + rollno.get(i));
                }
            }
        }



        public static void Q4UnPlaced(int nu_unplace) {
            System.out.println(nu_unplace+" "+"STUDENT LEFT");
        }



        public static int Q6SelectedStu(String Comp,int stud, HashMap<String, Integer> re_stud, HashMap<Integer, Integer> marks_tech, HashMap<Integer, Boolean> Placed,HashMap<Integer, ArrayList<String>> Applies_Comp) {
            if (re_stud.get(Comp) >= stud) {
                System.out.println("Roll Number of Selected Students");
                for (int i = 0; i < stud;i++) {
                    System.out.println(i + 1);
                    stud--;
                    Placed.put(i+1,true);
                }
            } else if (re_stud.get(Comp) < stud) {
                System.out.println("Roll Number of Selected Students");
                int check = 1;
                while (check <= re_stud.get(Comp)) {
                    int ele = marks_tech.get(1);
                    int idx = 1;
                    for (int i = 1; i < marks_tech.size()-1; i++) {
                        if (ele < marks_tech.get(i + 1)) {
                            ele = marks_tech.get(i + 1);
                            idx = i + 1;

                        }
                    }

                    System.out.println(idx);
                    Placed.put(idx,true);
                    if(Applies_Comp.containsKey(idx)){
                        ArrayList<String> arr = Applies_Comp.get(idx);
                        arr.add(Comp);
                        Applies_Comp.put(idx,arr);

                    }else{
                        ArrayList<String> arr = new ArrayList<>();
                        arr.add(Comp);
                        Applies_Comp.put(idx,arr);

                    }

                    stud--;
                    marks_tech.remove(ele);
                    check++;
                }

            } else {
                System.out.println("Company not Present");
            }
            return stud;

        }



        public static void Q8Detail_Student(int roll, HashMap<Integer, Boolean> Palaced, HashMap<Integer, Float> CGPA, HashMap<Integer,ArrayList<String>> Applied_comp, HashMap<Integer, String> Branch_stud) {
            if (!Palaced.get(roll)) {
                System.out.println(roll);
                System.out.println(CGPA.get(roll));
                System.out.println(Branch_stud.get(roll));
                System.out.println("Placement Status: Not placed");

            } else {
                System.out.println(roll);
                System.out.println(CGPA.get(roll));
                System.out.println(Branch_stud.get(roll));
                System.out.println("Placement Status: placed");
                System.out.println(Applied_comp.get(roll).get(0));
            }
        }

        public static void Q9Display(HashMap<Integer, ArrayList<String>> Applied_Comp, HashMap<Integer, ArrayList<Integer>> marks_tech, int roll_no) {
            if (!Applied_Comp.containsKey(roll_no)) {
                System.out.println("No student with the given roll number has an account.");
            } else {
                ArrayList<String> app = Applied_Comp.get(roll_no);
                ArrayList<Integer> MRK = marks_tech.get(roll_no);
                for(int i=0;i<app.size();i++){
                    System.out.println(app.get(i));
                }
                for(int i=0;i<MRK.size();i++){
                    System.out.println(MRK.get(i));
                }

            }

        }

    }





    public static void main(String[] args) {
        ArrayList<String> course = new ArrayList<>();
        HashMap<String, ArrayList<String>> Company = new HashMap<>();
        HashMap<String, Integer> reqd_stu = new HashMap<>();
        HashMap<Integer, Boolean> Placed = new HashMap<>();
        HashMap<String, Boolean> ClosedApp = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> marks = new HashMap<>();
        HashMap<Integer, ArrayList<String>> Applies_Comp = new HashMap<>();
        HashMap<String, Integer> Branch_Stu = new HashMap<>();
        HashMap<Integer, String> Branch_Stu2 = new HashMap<>();
        HashMap<Integer, Integer> marks_tech = new HashMap<>();
        HashMap<Integer, Float> CGPA = new HashMap<>();
        HashMap<String, Boolean> IsClosed = new HashMap<>();
         Scanner scrn = new Scanner(System.in);
         Companies co = new Companies();
         Student st = new Student();
        int Num_Stu = scrn.nextInt();
        for (int i = 0; i < Num_Stu; i++) {
            Float cgpa = scrn.nextFloat();
            String branch_stu = scrn.next();
            CGPA.put(i + 1, cgpa);
            Branch_Stu.put(branch_stu, i + 1);
            Branch_Stu2.put(i+1,branch_stu);
            Placed.put(i + 1, false);
        }
        while (Num_Stu > 0) {
            int Query = scrn.nextInt();
            if (Query == 1) {
                String Comp = scrn.next();
                System.out.println("Number Of Eligible Courses =");
                int Nu_ele_corse = scrn.nextInt();
                for (int i = 0; i < Nu_ele_corse; i++) {
                    String Elegible_Course = scrn.next();
                    course.add(Elegible_Course);
                }
                System.out.println("Number Of Required Students =");
                int Reqd_Stu = scrn.nextInt();
                IsClosed.put(Comp, false);
                Company.put(Comp, course);
                reqd_stu.put(Comp, Reqd_Stu);
                co.Q1AddCompany(Comp, course, Reqd_Stu, Company, reqd_stu,Branch_Stu,Applies_Comp,marks,marks_tech);
            } else if (Query == 2) {
                st.Q2PlacedStu(Placed);
            } else if (Query == 3) {
                co.Q3RmComp(IsClosed);

            } else if (Query == 4) {
                st.Q4UnPlaced(Num_Stu);
            } else if (Query == 5) {
                co.Q5OpenComp(IsClosed);
            } else if (Query == 6) {
                String Comp_name = scrn.next();
                int left = st.Q6SelectedStu(Comp_name,Num_Stu,reqd_stu,marks_tech,Placed,Applies_Comp);
                Num_Stu = left;

            }else if(Query==7){
                String Comp_name = scrn.next();
                co.Q7Display_comp(Comp_name,IsClosed,Company,reqd_stu);

            }else if(Query==8){
                int roll = scrn.nextInt();
                st.Q8Detail_Student(roll,Placed,CGPA,Applies_Comp,Branch_Stu2);
            }else  if(Query==9){
                int roll = scrn.nextInt();
                st.Q9Display(Applies_Comp,marks,roll);
            }else{
                System.out.println("Invalid Query");
            }
        }
        if(Num_Stu<1){
            return;
        }


    }


}