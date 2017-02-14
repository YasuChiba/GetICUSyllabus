import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by YasuhiraChiba on 2016/11/07.
 */
public class HtmlAnyalise {
    String directory = "";
    String saveFileName = "";
    FileWriter fw;
    PrintWriter pw;

    String year = Constatnts.year;


    HtmlAnyalise(String directory, String saveFileName) {
        this.directory = directory;
        this.saveFileName = saveFileName;

        try {
            fw = new FileWriter(directory + "/" + saveFileName, true);
            pw = new PrintWriter(new BufferedWriter(fw));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void Anyalise(String name) throws NullPointerException {
        BufferedReader br;
        FileReader filereader;


        String fpart = "<span id=\"ctl00_ContentPlaceHolder1_grv_course_ctl";
        String lpart = "_lbl_";
        String rgno = "rgno\">";
        String season = "season\">";
        String csno = "course_no\">";
        String lang = "lang\">";
        String etitle = "title_e\">";
        String jtitle = "title_j\">";
        String schedule = "schedule\">";
        String room = "room\">";
        String instructor = "instructor\">";
        String unit = "unit\" style=\"font-weight:bold\">";

        String rrgno = "", rseason = "", rcsno = "", rlang = "", retitle = "", rjtitle = "", rschedule = "", rroom = "", rinstructor = "", runit = "";
        String search;
        String tmp;


        String filename;
        filename = directory + "/" + name;
        File file = new File(filename);


        try {
            filereader = new FileReader(file);

            br = new BufferedReader(filereader);

            for (int j = 3; j < 13; j++) {

                for (int i = 0; i < 500; i++) {
                    String line = br.readLine();

                    if (j < 10) {
                        search = fpart + "0" + j + lpart + rgno;
                    } else {
                        search = fpart + j + lpart + rgno;
                    }

                    boolean a = isMatch(line, search);


                    if (a == true) {
                        int index = line.indexOf("\">");
                        index += "\">".length();
                        tmp = line.substring(index);
                        index = tmp.indexOf("</");
                        if (index == -1) {
                            line = br.readLine();
                            index = line.indexOf("</");
                            rrgno = tmp + line.substring(0, index);
                        } else {
                            rrgno = tmp.substring(0, index);
                        }
                        break;
                    } else {
                    }

                }

                for (int i = 0; i < 500; i++) {
                    String line = br.readLine();

                    if (j < 10) {
                        search = fpart + "0" + j + lpart + season;
                    } else {
                        search = fpart + j + lpart + season;
                    }

                    boolean a = isMatch(line, search);
                    if (a == true) {
                        int index = line.indexOf("\">");
                        index += "\">".length();
                        tmp = line.substring(index);
                        index = tmp.indexOf("</");
                        rseason = tmp.substring(0, index);

                        break;
                    } else {
                    }

                }
                for (int i = 0; i < 500; i++) {
                    String line = br.readLine();

                    if (j < 10) {
                        search = fpart + "0" + j + lpart + csno;
                    } else {
                        search = fpart + j + lpart + csno;
                    }

                    boolean a = isMatch(line, search);
                    if (a == true) {
                        int index = line.indexOf("\">");
                        index += "\">".length();
                        tmp = line.substring(index);
                        index = tmp.indexOf("</");
                        rcsno = tmp.substring(0, index);

                        break;
                    } else {
                    }

                }
                for (int i = 0; i < 500; i++) {
                    String line = br.readLine();

                    if (j < 10) {
                        search = fpart + "0" + j + lpart + lang;
                    } else {
                        search = fpart + j + lpart + lang;
                    }

                    boolean a = isMatch(line, search);
                    if (a == true) {
                        int index = line.indexOf("\">");
                        index += "\">".length();
                        tmp = line.substring(index);
                        index = tmp.indexOf("</");
                        rlang = tmp.substring(0, index);

                        break;
                    } else {
                    }

                }
                for (int i = 0; i < 500; i++) {
                    String line = br.readLine();

                    if (j < 10) {
                        search = fpart + "0" + j + lpart + etitle;
                    } else {
                        search = fpart + j + lpart + etitle;
                    }

                    boolean a = isMatch(line, search);
                    if (a == true) {
                        int index = line.indexOf("\">");
                        index += "\">".length();
                        tmp = line.substring(index);
                        index = tmp.indexOf("</");
                        if (index == -1) {
                            line = br.readLine();
                            index = line.indexOf("</");
                            retitle = tmp + line.substring(0, index);
                        } else {
                            retitle = tmp.substring(0, index);
                        }

                        break;
                    } else {
                    }

                }
                for (int i = 0; i < 500; i++) {
                    String line = br.readLine();

                    if (j < 10) {
                        search = fpart + "0" + j + lpart + jtitle;
                    } else {
                        search = fpart + j + lpart + jtitle;
                    }

                    boolean a = isMatch(line, search);
                    if (a == true) {
                        int index = line.indexOf("\">");
                        index += "\">".length();
                        tmp = line.substring(index);
                        index = tmp.indexOf("</");

                        if (index == -1) {
                            line = br.readLine();
                            index = line.indexOf("</");
                            rjtitle = tmp + line.substring(0, index);
                        } else {
                            rjtitle = tmp.substring(0, index);
                        }

                        break;
                    } else {
                    }

                }
                for (int i = 0; i < 500; i++) {
                    String line = br.readLine();

                    if (j < 10) {
                        search = fpart + "0" + j + lpart + schedule;
                    } else {
                        search = fpart + j + lpart + schedule;
                    }

                    boolean a = isMatch(line, search);
                    if (a == true) {
                        int index = line.indexOf("\">");
                        index += "\">".length();
                        tmp = line.substring(index);
                        index = tmp.indexOf("</");
                        if (index == -1) {
                            line = br.readLine();
                            index = line.indexOf("</");
                            rschedule = tmp + line.substring(0, index);
                        } else {
                            rschedule = tmp.substring(0, index);
                        }


                        break;
                    } else {
                    }

                }
                for (int i = 0; i < 500; i++) {
                    String line = br.readLine();

                    if (j < 10) {
                        search = fpart + "0" + j + lpart + room;
                    } else {
                        search = fpart + j + lpart + room;
                    }

                    boolean a = isMatch(line, search);
                    if (a == true) {
                        int index = line.indexOf("\">");
                        index += "\">".length();
                        tmp = line.substring(index);
                        index = tmp.indexOf("</");
                        rroom = tmp.substring(0, index);

                        break;
                    } else {
                    }

                }
                for (int i = 0; i < 500; i++) {
                    String line = br.readLine();

                    if (j < 10) {
                        search = fpart + "0" + j + lpart + instructor;
                    } else {
                        search = fpart + j + lpart + instructor;
                    }

                    boolean a = isMatch(line, search);
                    if (a == true) {
                        int index = line.indexOf("\">");
                        index += "\">".length();
                        tmp = line.substring(index);
                        index = tmp.indexOf("</");
                        rinstructor = tmp.substring(0, index);

                        break;
                    } else {
                    }

                }
                for (int i = 0; i < 500; i++) {
                    String line = br.readLine();

                    if (j < 10) {
                        search = fpart + "0" + j + lpart + unit;
                    } else {
                        search = fpart + j + lpart + unit;
                    }

                    boolean a = isMatch(line, search);
                    if (a == true) {
                        int index = line.indexOf("\">");
                        index += "\">".length();
                        tmp = line.substring(index);
                        index = tmp.indexOf("</");
                        runit = tmp.substring(0, index);

                        break;
                    } else {
                    }

                }


                int schedule_num = 0;
                int[] schedule_num_array = new int[20];
                int h = 0;
                int p11 = 0, p12 = 0, p13 = 0, p14 = 0, p15 = 0, p16 = 0, p21 = 0, p22 = 0, p23 = 0, p24 = 0, p25 = 0, p26 = 0, p31 = 0, p32 = 0, p33 = 0, p34 = 0, p35 = 0, p36 = 0, p41 = 0, p42 = 0, p43 = 0, p44 = 0, p45 = 0, p46 = 0, p51 = 0, p52 = 0, p53 = 0, p54 = 0, p55 = 0, p56 = 0, p61 = 0, p62 = 0, p63 = 0, p64 = 0, p65 = 0, p66 = 0, p71 = 0, p72 = 0, p73 = 0, p74 = 0, p75 = 0, p76 = 0, p81 = 0, p82 = 0, p83 = 0, p84 = 0, p85 = 0, p86 = 0, lp41 = 0, lp42 = 0, lp43 = 0, lp44 = 0, lp45 = 0, lp46 = 0;
                for (int k = 0; k < 10; k++) {
                    schedule_num_array[k] = 0;
                }


                ArrayList<Integer> schedule_list = new ArrayList<Integer>();

                for (int k = 0; k < rschedule.length(); k++) {
                    if (rschedule.charAt(k) == '\0') {
                        break;
                    } else if (rschedule.charAt(k) == ',') {

                    } else {
                        if ((rschedule.charAt(k) == '1') || (rschedule.charAt(k) == '2') || (rschedule.charAt(k) == '3') || (rschedule.charAt(k) == '4') || (rschedule.charAt(k) == '5') || (rschedule.charAt(k) == '6') || (rschedule.charAt(k) == '7') || (rschedule.charAt(k) == '8') || (rschedule.charAt(k) == '9') || (rschedule.charAt(k) == '*')) {


                            if (rschedule.charAt(k) == '1') {
                                schedule_num = 10;
                            }
                            if (rschedule.charAt(k) == '2') {
                                schedule_num = 20;
                            }
                            if (rschedule.charAt(k) == '3') {
                                schedule_num = 30;
                            }
                            if (rschedule.charAt(k) == '4') {
                                schedule_num = 40;
                            }
                            if (rschedule.charAt(k) == '5') {
                                schedule_num = 50;
                            }
                            if (rschedule.charAt(k) == '6') {
                                schedule_num = 60;
                            }
                            if (rschedule.charAt(k) == '7') {
                                schedule_num = 70;
                            }
                            if (rschedule.charAt(k) == '8') {
                                schedule_num = 80;
                            }
                            if (rschedule.charAt(k) == '*') {
                                schedule_num = 90;
                                k++;
                            }


                            if (rschedule.charAt(k + 2) == 'M') {
                                schedule_num += 1;
                                schedule_num_array[h] = schedule_num;
                                //printf("%d\n",schedule_num);
                                h++;

                            }
                            if (rschedule.charAt(k + 2) == 'T') {
                                if (rschedule.charAt(k + 3) == 'U') {
                                    schedule_num += 2;
                                    schedule_num_array[h] = schedule_num;
                                    //  printf("%d\n",schedule_num);
                                    h++;

                                }
                                if (rschedule.charAt(k + 3) == 'H') {
                                    schedule_num += 4;
                                    schedule_num_array[h] = schedule_num;
                                    //  printf("%d\n",schedule_num);
                                    h++;

                                }
                            }
                            if (rschedule.charAt(k + 2) == 'W') {
                                schedule_num += 3;
                                schedule_num_array[h] = schedule_num;
                                // printf("%d\n",schedule_num);
                                h++;

                            }
                            if (rschedule.charAt(k + 2) == 'F') {
                                schedule_num += 5;
                                schedule_num_array[h] = schedule_num;
                                //   printf("%d\n",schedule_num);
                                h++;

                            }
                            if (rschedule.charAt(k + 2) == 'S') {
                                schedule_num += 6;
                                schedule_num_array[h] = schedule_num;
                                //   printf("%d\n",schedule_num);
                                h++;

                            }
                            schedule_list.add(schedule_num);
                        }


                    }


                }


                for (int k = 0; k < 20; k++) {

                    switch (schedule_num_array[k]) {
                        case 11:
                            p11 = 1;
                            break;
                        case 12:
                            p12 = 1;
                            break;
                        case 13:
                            p13 = 1;
                            break;
                        case 14:
                            p14 = 1;
                            break;
                        case 15:
                            p15 = 1;
                            break;
                        case 16:
                            p16 = 1;
                            break;
                        case 21:
                            p21 = 1;
                            break;
                        case 22:
                            p22 = 1;
                            break;
                        case 23:
                            p23 = 1;
                            break;
                        case 24:
                            p24 = 1;
                            break;
                        case 25:
                            p25 = 1;
                            break;
                        case 26:
                            p26 = 1;
                            break;
                        case 31:
                            p31 = 1;
                            break;
                        case 32:
                            p32 = 1;
                            break;
                        case 33:
                            p33 = 1;
                            break;
                        case 34:
                            p34 = 1;
                            break;
                        case 35:
                            p35 = 1;
                            break;
                        case 36:
                            p36 = 1;
                            break;
                        case 41:
                            p41 = 1;
                            break;
                        case 42:
                            p42 = 1;
                            break;
                        case 43:
                            p43 = 1;
                            break;
                        case 44:
                            p44 = 1;
                            break;
                        case 45:
                            p45 = 1;
                            break;
                        case 46:
                            p46 = 1;
                            break;
                        case 51:
                            p51 = 1;
                            break;
                        case 52:
                            p52 = 1;
                            break;
                        case 53:
                            p53 = 1;
                            break;
                        case 54:
                            p54 = 1;
                            break;
                        case 55:
                            p55 = 1;
                            break;
                        case 56:
                            p56 = 1;
                            break;
                        case 61:
                            p61 = 1;
                            break;
                        case 62:
                            p62 = 1;
                            break;
                        case 63:
                            p63 = 1;
                            break;
                        case 64:
                            p64 = 1;
                            break;
                        case 65:
                            p65 = 1;
                            break;
                        case 66:
                            p66 = 1;
                            break;
                        case 71:
                            p71 = 1;
                            break;
                        case 72:
                            p72 = 1;
                            break;
                        case 73:
                            p73 = 1;
                            break;
                        case 74:
                            p74 = 1;
                            break;
                        case 75:
                            p75 = 1;
                            break;
                        case 76:
                            p76 = 1;
                            break;

                        case 81:
                            p81 = 1;
                            break;
                        case 82:
                            p82 = 1;
                            break;
                        case 83:
                            p83 = 1;
                            break;
                        case 84:
                            p84 = 1;
                            break;
                        case 85:
                            p85 = 1;
                            break;
                        case 86:
                            p86 = 1;
                            break;

                        case 91:
                            lp41 = 1;
                            break;
                        case 92:
                            lp42 = 1;
                            break;
                        case 93:
                            lp43 = 1;
                            break;
                        case 94:
                            lp44 = 1;
                            break;
                        case 95:
                            lp45 = 1;
                            break;
                        case 96:
                            lp46 = 1;
                            break;

                    }


                }


                String regex = "'";

                rrgno = rrgno.replaceAll(regex, "''");
                rcsno = rcsno.replaceAll(regex, "''");
                rlang = rlang.replaceAll(regex, "''");
                retitle = retitle.replaceAll(regex, "''");
                rjtitle = rjtitle.replaceAll(regex, "''");
                rroom = rroom.replaceAll(regex, "''");
                rinstructor = rinstructor.replaceAll(regex, "''");


                //時間ごとに分割しない方法     80番第はL4
                String schedule_string = "";
                if (schedule_list.size() != 0) {
                    for (int a = 0; a < schedule_list.size(); a++) {
                        schedule_string = schedule_string + schedule_list.get(a) + " ";
                    }
                } else {
                    schedule_string = "0";
                }


                String syllabus = "err";

                if (rseason.equals("Spring")) {
                    syllabus = rseason + year;
                }
                if (rseason.equals("Autumn")) {
                    syllabus = rseason + year;
                }
                if (rseason.equals("Winter")) {
                    syllabus = rseason + year;
                }
                pw.printf("insert into " + syllabus + "('rgno','season','csno','lang','etitle','jtitle','schedule','room','instructor','unit','schedule_string','s11','s12','s13','s14','s15','s16','s21','s22','s23','s24','s25','s26','s31','s32','s33','s34','s35','s36','s41','s42','s43','s44','s45','s46','s51','s52','s53','s54','s55','s56','s61','s62','s63','s64','s65','s66','s71','s72','s73','s74','s75','s76','s81','s82','s83','s84','s85','s86','s91','s92','s93','s94','s95','s96') values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d','%d');\n", rrgno, rseason, rcsno, rlang, retitle, rjtitle, rschedule, rroom, rinstructor, runit, schedule_string, p11, p12, p13, p14, p15, p16, p21, p22, p23, p24, p25, p26, p31, p32, p33, p34, p35, p36, p41, p42, p43, p44, p45, p46, p51, p52, p53, p54, p55, p56, p61, p62, p63, p64, p65, p66, p71, p72, p73, p74, p75, p76, p81, p82, p83, p84, p85, p86, lp41, lp42, lp43, lp44, lp45, lp46);


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void endOfProcess() {
        pw.close();
    }

    private boolean isMatch(String str1, String str2) throws NullPointerException {


        if (str1.matches(".*" + str2 + ".*")) {
            return true;
        } else {
            return false;
        }


    }


}


