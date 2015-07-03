// IStudentInterface.aidl
package com.gooker.aidlserver;

// Declare any non-default types here with import statements
import com.gooker.aidlserver.Student;
interface IStudentInterface {
    void addStudent(int age,String name);
    Student getStudent(String name);
}
