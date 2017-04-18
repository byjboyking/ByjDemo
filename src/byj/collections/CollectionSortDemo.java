package byj.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/**
 * 使用 Collections.sort 来实现集合排序 20140823
 *
 */
public class CollectionSortDemo {
    public static void main(String[] args) {
         //方式1：参与排序的列表的元素Student1必须实现Comparable接口的 
        ArrayList<Student1> al =  new ArrayList<Student1>(); 
        al.add(new Student1(2, "aora"));
        al.add(new Student1(1, "longyu")); 
        al.add(new Student1(3, "goso"));
        Collections.sort(al); 
        printElements(al);
         
        
       /*   //方式2：实现比较器（Comparator）接口。
        ArrayList<Student2> al = new ArrayList<Student2>();
        al.add(new Student2(2, "qingan"));
        al.add(new Student2(1, "longyu"));
        al.add(new Student2(3, "goso"));
        Collections.sort(al, new Student2.studentComparator());
        printElements(al);
        */
    }

    public static void printElements(Collection c) {
        Iterator it = c.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}

class Student1 implements Comparable {
    int num;
    String name;
    Student1(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public int compareTo(Object o) {
        Student1 s = (Student1) o;
        // 1. min -> max
        // return num > s.num ? 1 : (num == s.num ? 0 : -1);

        // 2. max -> min
        return num > s.num ? -1 : (num == s.num ? 0 : 1);
    }

    public String toString() {
        return "num = " + this.num + ",name = " + this.name;
    }
}

class Student2 {
    int num;
    String name;

    Student2(int num, String name) {
        this.num = num;
        this.name = name;
    }

    static class studentComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            Student2 s1 = (Student2) o1;
            Student2 s2 = (Student2) o2;
            
            // 1. min -> max
            int result = s1.num > s2.num ? 1 : (s1.num == s2.num ? 0 : -1);
            // 2. max -> min
            //int result = s1.num > s2.num ? -1 : (s1.num == s2.num ? 0 : 1);

            // 注意：此处在对比num相同时，再按照name的首字母比较。
            if (result == 0) {
                result = s1.name.compareTo(s2.name);
            }
            return result;
        }
    }

    public String toString() {
        return "num = " + this.num + ",name = " + this.name;
    }
}

