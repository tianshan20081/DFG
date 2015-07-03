// IStudentService.aidl
package com.gooker.dfg.aidl;

// Declare any non-default types here with import statements

import com.gooker.dfg.aidl.Student;
interface IStudentService {

    int getAge(String name);
    Student getInfo(int age,String name);
    //void setInfo(Student stu);
}
