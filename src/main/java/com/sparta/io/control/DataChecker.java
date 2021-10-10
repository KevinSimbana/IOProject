package com.sparta.io.control;

public class DataChecker {
    public static boolean isDataValid(String[] metadata) {
        //ID check
        int id = Integer.parseInt(metadata[0]);
        if (id < 0) {
            return false;
        }
        //Prefix check
        String prefix = metadata[1];
        if (!prefix.matches("[a-zA-Z]+\\.") ||  prefix.length() > 5) {
            return false;
        }
        //First name check
        String firstName = metadata[2];
        if (!prefix.matches("^[A-Z][-a-zA-Z]+$")) {
            return false;
        }
        //Middle Initial check
        String middleInitial = metadata[3];
        if (!middleInitial.matches("[a-zA-Z]")) {
            return false;
        }
        //Last name check
        String lastName = metadata[4];
        if (!lastName.matches("^[A-Z][-a-zA-Z]+$")) {
            return false;
        }
        //Gender check
        String gender = metadata[5];
        if (!gender.matches("[mfMF]")) {
            return false;
        }
        //Email check
        String email = metadata[6];
        if (!email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)" +
                "*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\" +
                "x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\" +
                "[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:" +
                "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])")) {
            return false;
        }
        //Date of birth check
//        String dob = metadata[7];
//        if (!dob.matches("")) {
//            return false;
//        }
//        //Date of joining check
//        String doj = metadata[8];
//        if (!doj.matches("")) {
//            return false;
//        }
        //Salary check
        int salary = Integer.parseInt(metadata[9]);
        if (salary < 0) {
            return false;
        }
        return true;
    }
}
